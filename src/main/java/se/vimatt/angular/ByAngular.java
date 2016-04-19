package se.vimatt.angular;


/**
 * The "middle layer" class which gives us the methods that return the specific Angular elements
 * <p>
 * Created by victor mattsson on 2016-03-29.
 */
public class ByAngular {

    private ByAngular() {
    }

    public static ByAngularModel model(String model) {
        return new ByAngularModel(model);
    }

    public static ByAngularBinding binding(String binding) {
        return new ByAngularBinding(binding);
    }

    public static ByAngularOptions options(String options) {
        return new ByAngularOptions(options);
    }

    public static ByAngularRepeater repeater(String repeater) {
        return new ByAngularRepeater(repeater);
    }

}
