package se.vimatt.angular.unit;

import static org.junit.Assert.*;
import org.junit.Test;
import se.vimatt.angular.ByAngular;
import se.vimatt.angular.ByAngularBinding;
import se.vimatt.angular.ByAngularModel;

/**
 * Created by victormattsson on 2016-04-14.
 */
public class ByAngularTest {

    @Test
    public void test1() {
        assertEquals(ByAngularBinding.class, ByAngular.binding("binding").getClass());
    }

    @Test
    public void test2() {
        assertEquals(ByAngularModel.class, ByAngular.model("model").getClass());
    }
}
