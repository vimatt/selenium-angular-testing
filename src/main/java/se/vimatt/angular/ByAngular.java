package se.vimatt.angular;


/**
 * Created by victormattsson on 2016-03-29.
 */
public class ByAngular {

    private ByAngular() {}

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
