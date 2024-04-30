package org.nightdivers.kupica.domain.hashtag;

import static org.nightdivers.kupica.domain.hashtag.Hashtag.MAX_TAG_NAME_WITH_HASH_LENGTH;

public class HashtagValidator {
    public static String validateTagName(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be empty");
        }

        tagName = "#" + eraseContinuousHash(tagName);

        if (tagName.length() < 2) {
            throw new IllegalArgumentException("Tag name must not be empty");
        }

        if (tagName.length() > MAX_TAG_NAME_WITH_HASH_LENGTH) {
            throw new IllegalArgumentException("Tag name must not exceed " + MAX_TAG_NAME_WITH_HASH_LENGTH + " characters");
        }

        return tagName;
    }

    private static int returnContinuousLastHashIndex(String tagName) {
        int continuousLastHashIndex = -1;
        for (int i = 0; i < tagName.length(); i++) {
            if (tagName.charAt(i) != '#') {
                break;
            }
            continuousLastHashIndex = i;

        }
        return continuousLastHashIndex;
    }

    private static String eraseContinuousHash(String tagName) {
        int continuousLastHashIndex = returnContinuousLastHashIndex(tagName);
        if (continuousLastHashIndex == -1) {
            return tagName;
        }

        return tagName.substring(continuousLastHashIndex + 1);
    }
}
