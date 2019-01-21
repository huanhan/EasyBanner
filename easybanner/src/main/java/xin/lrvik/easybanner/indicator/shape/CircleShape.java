package xin.lrvik.easybanner.indicator.shape;

import android.graphics.Path;

import xin.lrvik.easybanner.indicator.EasyDotIndicator;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class CircleShape implements BaseEasyShape {

    @Override
    public void setIndicatorsShape(Path path, EasyDotIndicator easyDotIndicator) {
        EasyDotIndicator.CenterPoint point;
        for (int i = 0; i < easyDotIndicator.getSize(); i++) {
            point = easyDotIndicator.getCenterPoint(i);
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();

            path.addCircle(centerX, centerY,
                    easyDotIndicator.getDef_radius() == 0 ? 5 : easyDotIndicator.getDef_radius(),
                    Path.Direction.CW);
        }
    }

    @Override
    public void setSelIndicatorsShape(Path path, EasyDotIndicator easyDotIndicator) {
        if (easyDotIndicator.getSize()>0) {
            EasyDotIndicator.CenterPoint point = easyDotIndicator.getCenterPoint();
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();
            if (point.getPosition() != easyDotIndicator.getSize() - 1) {
                centerX += (int) ((easyDotIndicator.getSelIndicatorWidth() + easyDotIndicator.getIndicatorMargin()) * point.getPositionOffset());
            }

            path.addCircle(centerX, centerY,
                    easyDotIndicator.getSel_radius() == 0 ? 5 : easyDotIndicator.getSel_radius(),
                    Path.Direction.CW);
        }
    }
}
