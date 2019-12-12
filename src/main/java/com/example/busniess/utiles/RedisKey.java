package com.example.busniess.utiles;

public class RedisKey {
    /*线上Redis禁止使用Keys正则匹配操作，容易引起缓存雪崩，最终数据库宕机,如
	Set<String> keySet = redisUtil.keys("posts_views::posts_views_id_*");
	所以此处用KEY标识浏览数和点赞数的键，然后存一个集合CODE表示文章ID,点赞数，也就是在外边包一层，里面取集合*/

    //人才需求浏览数量的key
    public static final String TALENT_VIEW_COUNT_KEY = "talent_view";
    //人才需求浏览数量每篇的key
    public static final String TALENT_VIEW_COUNT_CODE = "talent_view_count_";

    //人才需求点赞数量 key
    public static final String TALENT_APPROVE_COUNT_KEY = "talent_approve";
    //人才需求点赞数每篇的 key
    public static final String TALENT_APPROVE_COUNT_CODE = "talent_count_";

}
