package xin.lrvik.easybanner.indicator.shape;

import android.graphics.Path;
import android.graphics.RectF;

import xin.lrvik.easybanner.indicator.EasyDotIndicator;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class RectShape implements BaseEasyShape {

    @Override
    public void setIndicatorsShape(Path path, EasyDotIndicator easyDotIndicator) {
        EasyDotIndicator.CenterPoint point;
        for (int i = 0; i < easyDotIndicator.getSize(); i++) {
            point = easyDotIndicator.getCenterPoint(i);
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();

            int left = centerX - easyDotIndicator.getIndicatorWidth() / 2;
            int top = centerY - easyDotIndicator.getIndicatorHeight() / 2;
            int right = left + easyDotIndicator.getIndicatorWidth();
            int bottom = top + easyDotIndicator.getIndicatorHeight();

            if (easyDotIndicator.getDef_radius() != 0) {
                path.addRoundRect(new RectF(left, top, right, bottom), easyDotIndicator.getDef_radius(),
                        easyDotIndicator.getDef_radius(), Path.Direction.CW);
            } else {
                path.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
            }
        }
    }

    @Override
    public void setSelIndicatorsShape(Path path, EasyDotIndicator easyDotIndicator) {
        if (easyDotIndicator.getSize() > 0) {
            EasyDotIndicator.CenterPoint point = easyDotIndicator.getCenterPoint();
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();

            int left = centerX - easyDotIndicator.getSelIndicatorWidth() / 2;
            int top = centerY - easyDotIndicator.getSelIndicatorHeight() / 2;
            if (point.getPosition() != easyDotIndicator.getSize() - 1) {
                left += (int) ((easyDotIndicator.getSelIndicatorWidth() + easyDotIndicator.getIndicatorMargin()) * point.getPositionOffset());
            }
            int right = left + easyDotIndicator.getSelIndicatorWidth();
            int bottom = top + easyDotIndicator.getSelIndicatorHeight();

            if (easyDotIndicator.getSel_radius() != 0) {
                path.addRoundRect(new RectF(left, top, right, bottom), easyDotIndicator.getSel_radius(),
                        easyDotIndicator.getSel_radius(), Path.Direction.CW);
            } else {
                path.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
            }
        }
    }

}
