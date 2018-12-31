package xin.lrvik.easybanner.indicator.shape;

import android.graphics.Path;

import xin.lrvik.easybanner.indicator.DotIndicator;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public interface BaseEasyShape {

    void setIndicatorsShape(Path path, DotIndicator dotIndicator);

    void setSelIndicatorsShape(Path path, DotIndicator dotIndicator);
}
