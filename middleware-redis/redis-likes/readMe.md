# redis 点赞功能实现

## 场景分析
   用户需要相互点赞的功能
## 实现思路

1. 当有用户点赞取消赞时，分别调用接口，saveLiked2Redis、unlikeFromRedis 、incrementLikedCount、decrementLikedCount接口

2. 定时任务将redis的用户点赞记录和用户点赞数量分别存到数据库中 likedService.transLikedFromRedis2DB();  likedService.transLikedCountFromRedis2DB();

## 不足
1. 数据量太大，如果有500w用户，那可能点赞数据最大就是笛卡尔积，都存到数据库里面，会导致查询很慢