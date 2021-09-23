import {makeStyles, Theme} from "@material-ui/core";

export const useLinkPreviewStyles = makeStyles<Theme>((theme) => ({
    container: {
        width: 504,
        height: 372,
        marginTop: 5,
        borderRadius: 16,
        border: "1px solid rgb(207, 217, 222)",
    },
    linkCover: {
        borderRadius: "16px 16px 0px 0px",
        width: "100%",
    },
    siteLink: {
        color: "inherit",
        textDecoration: "none",
    },
    siteInfoWrapper: {
        padding: "3px 12px 12px 12px",
        lineHeight: "20px"
    },
    siteInfoTitle:{
        fontSize: 15,
        textOverflow: "ellipsis",
        whiteSpace: "nowrap",
        overflow: "hidden",
    },
    siteInfoDescription:{
        fontSize: 15,
        color: "rgb(83, 100, 113)",
        display: "-webkit-box",
        "-webkit-box-orient": "vertical",
        "-webkit-line-clamp": 2,
        overflow: "hidden",
        textOverflow: "ellipsis"
    },
    siteTitle: {
        marginTop: 5,
        height: 40,
        fontSize: 15,
        color: "rgb(83, 100, 113)",
        "& svg": {
            marginBottom: 2,
            marginRight: 3,
            verticalAlign: "bottom",
            fill: "rgb(83, 100, 113)",
            height: "1.10em",
        },
    },
}));