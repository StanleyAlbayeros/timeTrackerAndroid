package nucli;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vernon on 13/01/2017.
 */

public class BasicTask extends Task implements Serializable {
    public BasicTask(String name, String description, Project father) {
        super(name, description, father);
    }
}
