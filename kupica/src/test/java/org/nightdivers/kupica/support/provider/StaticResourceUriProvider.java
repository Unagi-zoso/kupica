package org.nightdivers.kupica.support.provider;

import java.util.List;

public class StaticResourceUriProvider {

    public static List<String> provideStaticResourceUriParameters() {
        return List.of(
                "/css/main-style.css",
                "/css/article-style.css",
                "/css/detail-article-modal-style.css",
                "/css/header-navbar-style.css",
                "/css/modal-style.css", // 5
                "/css/second-navbar.css",
                "/css/sidebar-style.css",
                "/css/slideshow-style.css",
                "/js/DetailArticleModalFormBtn.js",
                "/js/ModalFormBtn.js", // 10
                "/js/ScrollToTopBtn.js",
                "/js/SidebarBtn.js",
                "/js/SlideShow.js",
                "/js/UploadModalFunc.js",
                "/images/caret-up.svg", // 15
                "/images/chevron-left.svg",
                "/images/chevron-right.svg",
                "/images/circle-down.svg",
                "/images/comment-regular.svg",
                "/images/greater-than.svg", // 20
                "/images/heart-regular.svg"
        );
    }

}
