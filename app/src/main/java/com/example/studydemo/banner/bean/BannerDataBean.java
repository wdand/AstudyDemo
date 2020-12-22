package com.example.studydemo.banner.bean;

import com.example.studydemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerDataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public BannerDataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public BannerDataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<BannerDataBean> getTestData() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean(R.drawable.image1, "相信自己,你努力的样子真的很美", 1));
        list.add(new BannerDataBean(R.drawable.image2, "极致简约,梦幻小屋", 3));
        list.add(new BannerDataBean(R.drawable.image3, "超级卖梦人", 3));
        list.add(new BannerDataBean(R.drawable.image4, "夏季新搭配", 1));
        list.add(new BannerDataBean(R.drawable.image5, "绝美风格搭配", 1));
        list.add(new BannerDataBean(R.drawable.image6, "微微一笑 很倾城", 3));
        return list;
    }
    public static List<BannerDataBean> getYFWBannerData() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1432/7dc116d095341b9df0fd142ae76087e6.nwm.png", "双十二年终盛典", 1));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1341/1c10b70a172f8ca50d493b8b82d4fca8.nwm.png", "男性健康", 3));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/3938/b0c2b6deca87ed9ecbbaa645d1546ae4.nwm.png", "疫情专区", 3));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1934/a0212425247d4a6d104d5aad8155eb88.nwm.png", "心脑血管专区", 1));
        list.add(new BannerDataBean(R.drawable.image6, "微微一笑 很倾城", 3));
        return list;
    }

    public static List<BannerDataBean> getTestData2() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean(R.drawable.image7, "听风.赏雨", 3));
        list.add(new BannerDataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new BannerDataBean(R.drawable.image9, "爱美.人间有之", 3));
        list.add(new BannerDataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new BannerDataBean(R.drawable.image11, "生活的态度", 3));
        return list;
    }

    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    public static List<BannerDataBean> getTestDataVideo() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
        list.add(new BannerDataBean(R.drawable.image7, "听风.赏雨", 1));
        list.add(new BannerDataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new BannerDataBean(R.drawable.image9, "爱美.人间有之", 1));
        list.add(new BannerDataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new BannerDataBean(R.drawable.image11, "生活的态度", 1));
        return list;
    }

    public static List<BannerDataBean> getTestData3() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1432/7dc116d095341b9df0fd142ae76087e6.nwm.png", null, 1));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1341/1c10b70a172f8ca50d493b8b82d4fca8.nwm.png", null, 1));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/3938/b0c2b6deca87ed9ecbbaa645d1546ae4.nwm.png", null, 1));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1934/a0212425247d4a6d104d5aad8155eb88.nwm.png", null, 1));
        list.add(new BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608555008376&di=e1d5e68ea044e1ffa79e8e3f73f0ce94&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F9c16fdfaaf51f3de9ba8ee1194eef01f3a2979a8.jpg", null, 1));
        list.add(new BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608555008377&di=49cc1586c16bfa4f6db97e44ce875b98&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg", null, 1));
        list.add(new BannerDataBean("https://c1.yaofangwang.net/19/1341/1c10b70a172f8ca50d493b8b82d4fca8.nwm.png", null, 1));
        return list;
    }

    public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(getRandColor());
        }
        return list;
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
