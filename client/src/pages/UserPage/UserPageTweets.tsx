import React, {FC, ReactElement, useState} from 'react';
import {Button, Hidden, Typography} from "@material-ui/core";

import Tweet from '../../components/Tweet/Tweet';
import {useUserPageStyles} from "./UserPageStyles";
import AddTweetModal from "../../components/AddTweetModal/AddTweetModal";

interface UserPageTweetsProps {
    tweets?: any;
    activeTab: number;
    userProfileId?: number;
    myProfileId?: number;
    username?: string;
}

const UserPageTweets: FC<UserPageTweetsProps> = ({
                                                     tweets,
                                                     activeTab,
                                                     userProfileId,
                                                     myProfileId,
                                                     username
                                                 }): ReactElement => {
    const classes = useUserPageStyles();
    const [visibleAddTweet, setSetVisibleAddTweet] = useState<boolean>(false);

    const handleClickOpenAddTweet = (): void => {
        setSetVisibleAddTweet(true);
    };

    const onCloseAddTweet = (): void => {
        setSetVisibleAddTweet(false);
    };

    const renderTweets = () => {
        if (tweets?.length === 0 && activeTab === 0) {
            return (
                <div className={classes.textWrapper}>
                    <Typography className={classes.topic}>
                        {userProfileId === myProfileId ? (
                            "You haven’t any Tweets yet"
                        ) : (
                            `@${username} hasn’t any Tweets`
                        )}
                    </Typography>
                    <Typography className={classes.text}>
                        {userProfileId === myProfileId ? (
                            "When you send Tweets, they will show up here."
                        ) : (
                            "When they do, their Tweets show up here."
                        )}
                    </Typography>
                    {userProfileId === myProfileId ? (
                        <Button onClick={handleClickOpenAddTweet} variant="contained" color="primary">
                            <Hidden smDown>
                                Send Tweet
                            </Hidden>
                        </Button>
                    ) : null}
                </div>
            )
        } else if (tweets?.length === 0 && activeTab === 1) {
            return (
                <div className={classes.textWrapper}>
                    <Typography className={classes.topic}>
                        {userProfileId === myProfileId ? (
                            "You haven’t any replies yet"
                        ) : (
                            `@${username} hasn’t any replies`
                        )}
                    </Typography>
                    <Typography className={classes.text}>
                        {userProfileId === myProfileId ? (
                            "When you reply Tweets, they will show up here."
                        ) : (
                            "When they do, their replies show up here."
                        )}
                    </Typography>
                </div>
            )
        } else if (tweets?.length === 0 && activeTab === 2) {
            return (
                <div className={classes.textWrapper}>
                    <Typography className={classes.topic}>
                        {userProfileId === myProfileId ? (
                            "You haven’t Tweeted any photos or videos yet"
                        ) : (
                            `@${username} hasn’t Tweeted any photos or videos`
                        )}
                    </Typography>
                    <Typography className={classes.text}>
                        {userProfileId === myProfileId ? (
                            "When you send Tweets with photos or videos in them, it will show up here."
                        ) : (
                            "When they do, their media will show up here."
                        )}
                    </Typography>
                    {userProfileId === myProfileId ? (
                        <Button onClick={handleClickOpenAddTweet} variant="contained" color="primary">
                            <Hidden smDown>
                                Tweet a photo or video
                            </Hidden>
                        </Button>
                    ) : null}
                </div>
            )
        } else if (tweets?.length === 0 && activeTab === 3) {
            return (
                <div className={classes.textWrapper}>
                    <Typography className={classes.topic}>
                        {userProfileId === myProfileId ? (
                            "You don’t have any likes yet"
                        ) : (
                            `@${username} hasn’t liked any Tweets`
                        )}
                    </Typography>
                    <Typography className={classes.text}>
                        {userProfileId === myProfileId ? (
                            "Tap the heart on any Tweet to show it some love. When you do, it’ll show up here."
                        ) : (
                            "When they do, those Tweets will show up here."
                        )}
                    </Typography>
                </div>
            )
        } else {
            return tweets?.map((tweet: any) => (
                <Tweet
                    {...tweet}
                    key={tweet.id}
                    images={tweet.images}
                    userProfileId={userProfileId}
                    activeTab={activeTab}/>
            ))
        }
    };

    return (
        <>
            {renderTweets()}
            <AddTweetModal onClose={onCloseAddTweet} visible={visibleAddTweet}/>
        </>
    );
};

export default UserPageTweets;