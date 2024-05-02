package org.nightdivers.kupica.domain.hashtag;

import static org.nightdivers.kupica.domain.hashtag.Hashtag.MAX_TAG_NAME_WITH_HASH_LENGTH;

public class HashtagValidator {
    public static String returnValidTagName(String tagName) {
        if (tagName == null || tagName.isEmpty()) {
            throw new IllegalArgumentException("태그 이름은 비어있을 수 없습니다.");
        }

        tagName = "#" + eraseContinuousHash(tagName);

        if (tagName.length() < 2) {
            throw new IllegalArgumentException("태그 이름은 최소 2글자 이상이어야 합니다.");
        }

        if (tagName.length() > MAX_TAG_NAME_WITH_HASH_LENGTH) {
            throw new IllegalArgumentException("태그 이름은 " + MAX_TAG_NAME_WITH_HASH_LENGTH + " 을 넘을 수 없습니다.");
        }

        return tagName;
    }

    private static int returnContinuousHashLastIndex(String tagName) {
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
        int continuousHashLastIndex = returnContinuousHashLastIndex(tagName);
        if (continuousHashLastIndex == -1) {
            return tagName;
        }

        return tagName.substring(continuousHashLastIndex + 1);
    }
}
