# redis 点赞功能实现

## 场景分析
   用户需要相互点赞的功能
## 实现思路

1. 当有用户点赞取消赞时，分别调用接口，saveLiked2Redis、unlikeFromRedis 、incrementLikedCount、decrementLikedCount接口

2. 定时任务将redis的用户点赞记录和用户点赞数量分别存到数据库中 likedService.transLikedFromRedis2DB();  likedService.transLikedCountFromRedis2DB();

## 不足
1. 数据量太大，如果有500w用户，那可能点赞数据最大就是笛卡尔积，都存到数据库里面，会导致查询很慢

## 扩展
bitmap简介：

　　bitmap时一连串的二进制数字（0,1），每位所在的位置为偏移（offset），在bitmap上可以执行and、or、xor以及其他操作。

位图计数：

　　位图计数 的意思是统计bitmap中值为1的位的个数，位统计的效率时很高的。

redis中允许使用二进制的key和二进制的value，bitmap就是二进制的value。



点赞/取消点赞：

　　假设用户ID为100，对照片ID为100的照片进行点赞。首先根据照片ID生成数据存储的redis key，比如生成策略是like_photo:{photo_id}，用户ID为1000的用户点赞只需将like_photo:100的第1000位设置为1即可（取消置为0）。

　　redis setbit操作的时间复杂度为O（1），所以这种点赞方式十分高效。

当前是否点赞：

　　用户打开图片的时候需要查询当前是否点赞过该照片，查询是否点赞可以通过redis getbit操作实现。

查询点赞总次数：

　　比如需要显示照片ID为1000的照片的获赞总次数，只需对like_photo:1000进行位图计数操作即可：bitcount。时间复杂度为O(N)。个人以为可以在照片表中加一个字段记录获赞总次数，这样就不用循环统计各个照片的获赞次数。

redis还提供了bittop等其他一些API，可以实现一些有趣的事。

局限性：

　　当用户量很大的时候，比如千万级用户量的时候，最坏的情况下一个点赞bitmap需要消耗的内存为10000000/8/1024/1024=1.19MB,