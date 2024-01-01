package edu.scau.common.utils;


import edu.scau.common.constant.RedisEnum;

public class RedisKeyUtil {

    /**
     * 某个实体赞的key
     * key ---> like:entity:entityType:entityId
     *
     * @param entityType 实体类型
     * @param entityId   实体id
     * @return key
     */
    public static String getEntityLikeKey(int entityType, Long entityId) {
        return RedisEnum.PREFIX_ENTITY_LIKE.getValue() + RedisEnum.SPLIT.getValue() + entityType + RedisEnum.SPLIT.getValue() + entityId;
    }

    /**
     * 某个用户累计的赞的key
     * <p>
     * key ---> like:user:userId -> int
     *
     * @param userId
     * @return
     */
    public static String getUserLikeKey(Long userId) {
        return RedisEnum.PREFIX_USER_LIKE.getValue() + RedisEnum.SPLIT.getValue() + userId;
    }

    /**
     * 某个用户关注某个实体的集合key
     * <p>
     * key
     * ---> followee:userId:entityType
     * ---> zset(entityId,now),zset为有序集合,以now作为排序分数
     * now表示当前时间的时间数,可以根据时间大小排序
     *
     * @param userId
     * @param entityType
     * @return
     */
    public static String getFolloweeKey(Long userId, int entityType) {
        return RedisEnum.PREFIX_FOLLOWEE.getValue() + RedisEnum.SPLIT.getValue() + userId + RedisEnum.SPLIT.getValue() + entityType;
    }

    /**
     * 某个实体拥有的粉丝集合key
     * <p>
     * key ---> follower:entityType:entityId -> zset(userId,now)
     *
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollowerKey(int entityType, Long entityId) {
        return RedisEnum.PREFIX_FOLLOWER.getValue() + RedisEnum.SPLIT.getValue() + entityType + RedisEnum.SPLIT.getValue() + entityId;
    }


    /**
     * 用户的key
     *
     * @param userId
     * @return
     */
    public static String getUserKey(Long userId) {
        return RedisEnum.PREFIX_USER.getValue() + RedisEnum.SPLIT.getValue() + userId;
    }

    /**
     * 用户登陆信息的key
     *
     * @param userId
     * @return
     */
    public static String getLoginUserKey(Long userId) {
        return RedisEnum.LOGIN_USER.getValue() + userId;
    }

    /**
     * 用户今日任务的key
     *
     * @param userId
     * @return
     */
    public static String getTodayTaskKey(Long userId) {
        return RedisEnum.PREFIX_TODAY_TASK.getValue() + RedisEnum.PREFIX_USER.getValue() + userId;
    }

    /**
     * 用户打卡通知的key
     */
    public static String getUserTodayTaskNoticeKey(Long userId) {
        return RedisEnum.PREFIX_NOTICE.getValue() + RedisEnum.PREFIX_TODAY_TASK.getValue() + RedisEnum.PREFIX_USER.getValue() + userId;
    }

    public static String getTodayTaskNoticeKey() {
        return RedisEnum.PREFIX_NOTICE.getValue() + RedisEnum.PREFIX_TODAY_TASK.getValue();
    }

    /**
     * post set的key
     */
    public static String getPostScoreSetKey() {
        return RedisEnum.PREFIX_POST.getValue() + RedisEnum.PREFIX_SCORE.getValue();
    }

    public static String getCarouselKey() {
        return RedisEnum.CAROUSEL.getValue();
    }
}
