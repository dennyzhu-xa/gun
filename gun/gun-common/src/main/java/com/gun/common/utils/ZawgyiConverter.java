package com.gun.common.utils;

import com.google.myanmartools.ZawgyiDetector;
import com.ibm.icu.text.Transliterator;

public class ZawgyiConverter {
	private static final ZawgyiDetector detector = new ZawgyiDetector();
    private static final Transliterator converter = Transliterator.getInstance("Zawgyi-my");
    
    public static String zawgyiToUnicode(String src){
    	double score1 = detector.getZawgyiProbability(src);
    	if (score1 > 0.999){
    		return converter.transliterate(src);
    	}
    	return src;
    }
}
