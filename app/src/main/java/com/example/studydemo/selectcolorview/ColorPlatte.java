package com.example.studydemo.selectcolorview;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class ColorPlatte {
    public static final String TRANSPARENT_COLOR = "#00000000";

    public static final String[] COLOR_PLATTES = {
            "#FFFFFF",
            "#E6E6E6",
            "#CDCDCD",
            "#ABABAB",
            "#8C8C8C",
            "#8C8C8C",
            "#545454",
            "#333333",
            "#1A1A1A",
            "#000000",

            "#FF9999",
            "#FFCC99",
            "#FEFD99",
            "#CCFF99",
            "#99FF99",
            "#99FFFF",
            "#99CCFF",
            "#9898FC",
            "#CC99FD",
            "#FF98FC",

            "#FF2905",
            "#FF8015",
            "#FEFE32",
            "#8AFF14",
            "#00FF00",
            "#00FFFF",
            "#0080FF",
            "#1922FB",
            "#7F27FC",
            "#FF37FC",

            "#800000",
            "#804000",
            "#808000",
            "#408000",
            "#008000",
            "#008080",
            "#004080",
            "#000080",
            "#400080",
            "#800080"
    };

    /**
     * 涂鸦颜色
     * 跟其他面板不同的是，第一个为透明色，当作橡皮擦，第二个是白色
     */
    public static String[] getDoodleColors() {
        String[] result = COLOR_PLATTES.clone();
//        //TODO 本期先不要橡皮擦
//        result[0] = TRANSPARENT_COLOR;
//        result[1] = "#FFFFFF";
        return result;
    }

    public static final int SHOW_FULL_COLOR = 1;
    public static final int SHOW_RECENT_COLOR = 2;

    //TODO 刺绣的文件名，对应一个名字，在UI面板上显示，后续建议该名字，由后台返回
    public static final Map<String, String> embroidery_extra_name_map = new HashMap<>();

    public static String getEmbroideryExtraName(String nameId) {
        if(embroidery_extra_name_map.size() == 0)
            initEmbroideryExtraNameMap();

        String result = embroidery_extra_name_map.get(nameId);
        if(TextUtils.isEmpty(result))
            result = "";

        return result;
    }
    private static void initEmbroideryExtraNameMap() {
        embroidery_extra_name_map.put("msa_1.1", "WHITE");
        embroidery_extra_name_map.put("msa_1.2", "PEARL RIVER GARY");
        embroidery_extra_name_map.put("msa_1.3", "RHINO GRAY");
        embroidery_extra_name_map.put("msa_1.4", "STONE GRAY");
        embroidery_extra_name_map.put("msa_1.5", "LAVA GRAY");
        embroidery_extra_name_map.put("msa_1.6", "STEEL GRAY");
        embroidery_extra_name_map.put("msa_1.7", "ASH GRAY");
        embroidery_extra_name_map.put("msa_1.8", "SHADOW GRAY");
        embroidery_extra_name_map.put("msa_1.9", "CHARCOAL GRAY");
        embroidery_extra_name_map.put("msa_1.10", "BLACK");
        embroidery_extra_name_map.put("msa_1.11", "LAVENDER VIOLET");
        embroidery_extra_name_map.put("msa_1.12", "PEACH YELLOW");
        embroidery_extra_name_map.put("msa_2.1", "BANANA YELLOW");
        embroidery_extra_name_map.put("msa_2.2", "MOSS GREEN");
        embroidery_extra_name_map.put("msa_2.3", "SAGE GREEN");
        embroidery_extra_name_map.put("msa_2.4", "SKY BLUE");
        embroidery_extra_name_map.put("msa_2.5", "BABY BLUE");
        embroidery_extra_name_map.put("msa_2.6", "GRAPE VIOLET");
        embroidery_extra_name_map.put("msa_2.7", "ORCHID VIOLET");
        embroidery_extra_name_map.put("msa_2.8", "LILAC VIOLET");
        embroidery_extra_name_map.put("msa_2.9", "RED");
        embroidery_extra_name_map.put("msa_2.10", "HONEY ORANGE");
        embroidery_extra_name_map.put("msa_2.11", "YELLOW");
        embroidery_extra_name_map.put("msa_2.12", "ENGLISH APPLE");
        embroidery_extra_name_map.put("msa_3.1", "KELLY GREEN");
        embroidery_extra_name_map.put("msa_3.2", "BY THE SEA BLUE");
        embroidery_extra_name_map.put("msa_3.3", "AIR FORCE BLUE");
        embroidery_extra_name_map.put("msa_3.4", "BLUE");
        embroidery_extra_name_map.put("msa_3.5", "ELECTRIC VIOLET");
        embroidery_extra_name_map.put("msa_3.6", "LOLLIPOP VIOLET");
        embroidery_extra_name_map.put("msa_3.7", "U.S.FLAG");
        embroidery_extra_name_map.put("msa_3.8", "APRICOT ORANGE");
        embroidery_extra_name_map.put("msa_3.9", "CORN YELLOW");
        embroidery_extra_name_map.put("msa_3.10", "GREEN");
        embroidery_extra_name_map.put("msa_3.11", "FOREST GREEN");
        embroidery_extra_name_map.put("msa_3.12", "TURKISH BLUE");
        embroidery_extra_name_map.put("msa_4.1", "STEEL BLUE");
        embroidery_extra_name_map.put("msa_4.2", "SPACE BLUE");
        embroidery_extra_name_map.put("msa_4.3", "ROYAL VIOLET");
        embroidery_extra_name_map.put("msa_4.4", "VIOLET");
        embroidery_extra_name_map.put("msb_1.1", "ABALONE GRAY");
        embroidery_extra_name_map.put("msb_1.2", "THUNDER GRAY");
        embroidery_extra_name_map.put("msb_1.3", "HARBOR GRAY");
        embroidery_extra_name_map.put("msb_1.4", "FOSSIL GRAY");
        embroidery_extra_name_map.put("msb_1.5", "ASH GRAY");
        embroidery_extra_name_map.put("msb_1.6", "MINK GRAY");
        embroidery_extra_name_map.put("msb_1.7", "STONE GRAY");
        embroidery_extra_name_map.put("msb_1.8", "SHADOW GRAY");
        embroidery_extra_name_map.put("msb_1.9", "CHARCOAL GRAY");
        embroidery_extra_name_map.put("msb_1.10", "BLACK");
        embroidery_extra_name_map.put("msb_1.11", "TAFFY PINK");
        embroidery_extra_name_map.put("msb_1.12", "ECRU YELLOW");
        embroidery_extra_name_map.put("msb_2.1", "BANANA YELLOW");
        embroidery_extra_name_map.put("msb_2.2", "MOSS GREENY");
        embroidery_extra_name_map.put("msb_2.3", "SAGE GREEN");
        embroidery_extra_name_map.put("msb_2.4", "NILE BLUE");
        embroidery_extra_name_map.put("msb_2.5", "PIGEON BLUE");
        embroidery_extra_name_map.put("msb_2.6", "ORCHID VIOLET");
        embroidery_extra_name_map.put("msb_2.7", "ELECTRIC VIOLET");
        embroidery_extra_name_map.put("msb_2.8", "PLUM VIOLET");
        embroidery_extra_name_map.put("msb_2.9", "RED");
        embroidery_extra_name_map.put("msb_2.10", "APRICOT ORANGE");
        embroidery_extra_name_map.put("msb_2.11", "GOLDEN ORANGE");
        embroidery_extra_name_map.put("msb_2.12", "LIME GREEN");
        embroidery_extra_name_map.put("msb_3.1", "GREEN");
        embroidery_extra_name_map.put("msb_3.2", "SKY BLUE");
        embroidery_extra_name_map.put("msb_3.3", "CAROLINA BLUE");
        embroidery_extra_name_map.put("msb_3.4", "EGYPTIAN BLUE");
        embroidery_extra_name_map.put("msb_3.5", "GRAPE VIOLET");
        embroidery_extra_name_map.put("msb_3.6", "FUCHSIA PINK");
        embroidery_extra_name_map.put("msb_3.7", "U.S FLAG RED");
        embroidery_extra_name_map.put("msb_3.8", "CARROT ORANGE");
        embroidery_extra_name_map.put("msb_3.9", "CORN YELLOW");
        embroidery_extra_name_map.put("msb_3.10", "KELLY GREEN");
        embroidery_extra_name_map.put("msb_3.11", "FOREST GREEN");
        embroidery_extra_name_map.put("msb_3.12","TURKISH BLUE");
        embroidery_extra_name_map.put("msb_4.1", "CARIBBEAN SEA");
        embroidery_extra_name_map.put("msb_4.2", " BLUE");
        embroidery_extra_name_map.put("msb_4.3", "MAUVE VIOLET");
        embroidery_extra_name_map.put("msb_4.4", "VIOLET");
    }
}
