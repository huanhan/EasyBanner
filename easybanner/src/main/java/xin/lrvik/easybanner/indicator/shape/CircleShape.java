package xin.lrvik.easybanner.indicator.shape;

import android.graphics.Path;
import android.graphics.RectF;

import xin.lrvik.easybanner.indicator.DotIndicator;

/**
 * Author by 豢涵, Email huanhanfu@126.com, Date on 2018/12/31.
 */
public class CircleShape implements BaseEasyShape {

    @Override
    public void setIndicatorsShape(Path path, DotIndicator dotIndicator) {
        DotIndicator.CenterPoint point;
        for (int i = 0; i < dotIndicator.getSize(); i++) {
            point = dotIndicator.getCenterPoint(i);
            int centerX = point.getCenterX();
            int centerY = point.getCenterY();

            path.addCircle(centerX, centerY,
                    dotIndicator.getDef_radius() == 0 ? 5 : dotIndicator.getDef_radius(),
                    Path.Direction.CW);
        }
    }

    @Override
    public void setSelIndicatorsShape(Path path, DotIndicator dotIndicator) {
        DotIndicator.CenterPoint point = dotIndicator.getCenterPoint();
        int centerX = point.getCenterX();
        int centerY = point.getCenterY();
        if (point.getPosition() != dotIndicator.getSize() - 1) {
            centerX += (int) ((dotIndicator.getSelIndicatorWidth() + dotIndicator.getIndicatorMargin()) * point.getPositionOffset());
        }


        path.addCircle(centerX, centerY,
                dotIndicator.getSel_radius() == 0 ? 5 : dotIndicator.getSel_radius(),
                Path.Direction.CW);

    }
}
