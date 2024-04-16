package ${package};

import com.edriving.restboot.runtime.RestBoot;

public class Application {
    public static void main(String[] args) throws Exception {
        new RestBoot()
                .scanPackage(Application.class.getPackageName())
                .start(args);
    }
}
