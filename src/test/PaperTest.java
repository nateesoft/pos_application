/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

/**
 *
 * @author softpos-support
 */
public class PaperTest {
    public static void main(String[] args) {
        String p = MediaSizeName.ISO_A1.toString();
        System.out.println(p);
        
        final MediaSize size = MediaSize.getMediaSizeForName(MediaSizeName.ISO_A1);
        double pX = size.getX(MediaSize.MM);
        double pY = size.getY(MediaSize.MM);
        System.out.println(pX+","+pY);
    }
}