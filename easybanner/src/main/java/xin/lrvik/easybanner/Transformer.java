package xin.lrvik.easybanner;

import android.support.v4.view.ViewPager.PageTransformer;

import xin.lrvik.easybanner.transformer.AccordionTransformer;
import xin.lrvik.easybanner.transformer.BackgroundToForegroundTransformer;
import xin.lrvik.easybanner.transformer.CubeInTransformer;
import xin.lrvik.easybanner.transformer.CubeOutTransformer;
import xin.lrvik.easybanner.transformer.DefaultTransformer;
import xin.lrvik.easybanner.transformer.DepthPageTransformer;
import xin.lrvik.easybanner.transformer.FlipHorizontalTransformer;
import xin.lrvik.easybanner.transformer.FlipVerticalTransformer;
import xin.lrvik.easybanner.transformer.ForegroundToBackgroundTransformer;
import xin.lrvik.easybanner.transformer.RotateDownTransformer;
import xin.lrvik.easybanner.transformer.RotateUpTransformer;
import xin.lrvik.easybanner.transformer.ScaleInOutTransformer;
import xin.lrvik.easybanner.transformer.StackTransformer;
import xin.lrvik.easybanner.transformer.TabletTransformer;
import xin.lrvik.easybanner.transformer.ZoomInTransformer;
import xin.lrvik.easybanner.transformer.ZoomOutSlideTransformer;
import xin.lrvik.easybanner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
