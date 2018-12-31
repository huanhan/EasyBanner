package xin.lrvik.easybanner.indicator.shape;

import android.graphics.Path;
import android.graphics.RectF;

import xin.lrvik.easybanner.indicator.DotIndicator;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class RectShape implements BaseEasyShape {

    @Override
    public void setIndicatorsShape(Path path, DotIndicator dotIndicator) {
        DotIndicator.CenterPoint point;
        for (int i = 0; i < dotIndicator.getSize(); i++) {
            point = dotIndicator.getCenterPoint(i);
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();

            int left = centerX - dotIndicator.getIndicatorWidth() / 2;
            int top = centerY - dotIndicator.getIndicatorHeight() / 2;
            int right = left + dotIndicator.getIndicatorWidth();
            int bottom = top + dotIndicator.getIndicatorHeight();

            if (dotIndicator.getDef_radius() != 0) {
                path.addRoundRect(new RectF(left, top, right, bottom), dotIndicator.getDef_radius(),
                        dotIndicator.getDef_radius(), Path.Direction.CW);
            } else {
                path.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
            }
        }
    }

    @Override
    public void setSelIndicatorsShape(Path path, DotIndicator dotIndicator) {
        DotIndicator.CenterPoint point = dotIndicator.getCenterPoint();
        int centerX = point.getCenterX();
        int centerY = point.getCenterY();

        int left = centerX - dotIndicator.getSelIndicatorWidth() / 2;
        int top = centerY - dotIndicator.getSelIndicatorHeight() / 2;
        if (point.getPosition() != dotIndicator.getSize() - 1) {
            left += (int) ((dotIndicator.getSelIndicatorWidth() + dotIndicator.getIndicatorMargin()) * point.getPositionOffset());
        }
        int right = left + dotIndicator.getSelIndicatorWidth();
        int bottom = top + dotIndicator.getSelIndicatorHeight();

        if (dotIndicator.getSel_radius() != 0) {
            path.addRoundRect(new RectF(left, top, right, bottom), dotIndicator.getSel_radius(),
                    dotIndicator.getSel_radius(), Path.Direction.CW);
        } else {
            path.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
        }
    }

}
