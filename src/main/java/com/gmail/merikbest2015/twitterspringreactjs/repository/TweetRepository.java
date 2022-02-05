package com.gmail.merikbest2015.twitterspringreactjs.repository;

import com.gmail.merikbest2015.twitterspringreactjs.model.Tweet;
import com.gmail.merikbest2015.twitterspringreactjs.repository.projection.TweetProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT new com.gmail.merikbest2015.twitterspringreactjs.repository.projection.TweetProjection(" +
            "t.id, t.text, t.dateTime, t.scheduledDate, t.addressedUsername, t.addressedId, t.addressedTweetId, t.replyType, " +
            "t.link ,t.linkTitle, t.linkDescription, t.linkCover, t.linkCoverSize, t.user.id, t.user.email, " +
            "t.user.fullName, t.user.username, t.user.avatar.id, t.user.avatar.src, i.id, i.src, " +
            "qt.id, qt.text, qt.dateTime, qt.link, qt.linkTitle, qt.linkDescription, qt.linkCover, qt.linkCoverSize, " +
            "uqt.id, uqt.email, uqt.fullName, uqt.username, uqta.id, uqta.src, " +
            "tp.id, tp.dateTime, tpc.id, tpc.choice, tpcu.id) " +
            "FROM Tweet t " +
            "LEFT JOIN t.images i " +
            "LEFT JOIN t.quoteTweet qt " +
            "LEFT JOIN qt.user uqt " +
            "LEFT JOIN uqt.avatar uqta " +
            "LEFT JOIN t.poll tp " +
            "LEFT JOIN tp.pollChoices tpc " +
            "LEFT JOIN tpc.votedUser tpcu " +
            "WHERE t.id = :tweetId")
    Optional<TweetProjection> findTweetById(Long tweetId);

    @Query("SELECT tweet FROM Tweet tweet " +
            "WHERE tweet.addressedUsername IS NULL " +
            "AND tweet.scheduledDate IS NULL " +
            "ORDER BY tweet.dateTime DESC")
    Page<Tweet> findAllTweets(Pageable pageable);

    @Query("SELECT tweet FROM Tweet tweet WHERE tweet.user.id = :userId AND tweet.scheduledDate IS NULL")
    List<Tweet> findAllByUserId(Long userId);

    @Query("SELECT tweet FROM Tweet tweet WHERE tweet.scheduledDate <= :scheduledDate")
    List<Tweet> findAllByScheduledDate(LocalDateTime scheduledDate);

    @Query("SELECT tweet FROM Tweet tweet WHERE tweet.user.id = :userId " +
            "AND tweet.scheduledDate IS NOT NULL " +
            "ORDER BY tweet.scheduledDate DESC")
    List<Tweet> findAllScheduledTweetsByUserId(Long userId);

    @Query("SELECT tweet FROM Tweet tweet " +
            "WHERE tweet.scheduledDate IS NULL " +
            "AND tweet.text LIKE CONCAT('%',:text,'%')")
    List<Tweet> findAllByText(String text);

    @Query("SELECT tweet FROM Tweet tweet " +
            "WHERE tweet.scheduledDate IS NULL " +
            "AND tweet.text LIKE CONCAT('%','youtu','%')")
    Page<Tweet> findAllTweetsWithVideo(Pageable pageable);

    @Query("SELECT tweet FROM Tweet tweet " +
            "JOIN tweet.images image " +
            "WHERE tweet.scheduledDate IS NULL " +
            "AND image.id IS NOT NULL " +
            "ORDER BY tweet.dateTime DESC")
    Page<Tweet> findAllTweetsWithImages(Pageable pageable);

    @Query("SELECT tweet FROM Tweet tweet " +
            "JOIN tweet.images image " +
            "WHERE tweet.scheduledDate IS NULL " +
            "AND image.id IS NOT NULL " +
            "AND tweet.user.id = :userId " +
            "OR UPPER('youtu') LIKE UPPER('%youtu%') " +
            "ORDER BY tweet.dateTime DESC")
    Page<Tweet> findAllUserMediaTweets(Long userId, Pageable pageable);

    @Query("SELECT tweet FROM Tweet tweet WHERE tweet.quoteTweet.id = :quoteId")
    List<Tweet> findByQuoteTweetId(Long quoteId);

    @Query("SELECT tweet FROM Tweet tweet " +
            "WHERE tweet.user.id = :userId " +
            "AND tweet.addressedUsername IS NULL " +
            "AND tweet.scheduledDate IS NULL " +
            "ORDER BY tweet.dateTime DESC")
    List<Tweet> findTweetsByUserId(Long userId);

    @Query("SELECT tweet FROM Tweet tweet " +
            "WHERE tweet.user.id = :userId " +
            "AND tweet.addressedUsername IS NOT NULL " +
            "AND tweet.scheduledDate IS NULL " +
            "ORDER BY tweet.dateTime DESC")
    List<Tweet> findRepliesByUserId(Long userId);

    @Query("SELECT user.pinnedTweet FROM User user WHERE user.id = :userId")
    Optional<Tweet> getPinnedTweetByUserId(Long userId);
}
