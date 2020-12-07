package com.bingkong.bkbase.model;

public class SinglePostInfo {

    /*
    {
        "code": 0,
            "data": {
        "detail": {
            "id": "6116ac32-c16e-11e8-bc30-00163e085727",
                    "originalId": "faeb26f6-bb48-11e8-bc30-00163e085727",
                    "userId": "220c9e19-8548-48ad-85b9-518f14210fa9",
                    "username": "uk",
                    "firstName": "",
                    "lastName": "",
                    "avatarUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/avatar\/6.jpg",
                    "productId": null,
                    "productThumbnail": "",
                    "body": "test",
                    "photoUrl": "",
                    "createdAt": "2018-09-26T17:27:25+08:00",
                    "comments": "0",
                    "likes": "1",
                    "isLiked": 0
                    	"originalPostInfo": {
                        "id": "8e87389e-c22f-11e8-bc30-00163e085727",
                        "originalId": "8e87389e-c22f-11e8-bc30-00163e085727",
                        "userId": "220c9e19-8548-48ad-85b9-518f14210fa9",
                        "username": "uk",
                        "firstName": "",
                        "lastName": "",
                        "avatarUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/avatar\/6.jpg",
                        "productId": null,
                        "productThumbnail": "",
                        "body": "Bbbbbbbbbbb",
                        "photoUrl": "https:\/\/unokiwidev.yotach.net\/uploads\/2018\/09\/27\/ce6fa8229bb54c40a53e8f5b0b846a4e.jpg",
                        "createdAt": "2018-09-27T16:30:14+08:00",
                        "comments": "0",
                        "likes": "0",
                        "isLiked": 0
                    }
        }
    },
        "message": "success"
    }
    */
    CommentInfoBean detail;
    CommentInfoBean originalPostInfo;

    public CommentInfoBean getDetail() {
        return detail;
    }

    public CommentInfoBean getOriginalPost() {
        return originalPostInfo;
    }
}
