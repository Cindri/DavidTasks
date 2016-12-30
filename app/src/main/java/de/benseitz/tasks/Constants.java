package de.benseitz.tasks;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by David on 18.12.2016.
 */
public class Constants {
    public static final String TASK_STORAGE_NAME = "storeTasks";
    public static final Type TYPE_ARRAY_LIST = new TypeToken<ArrayList<Task>>(){}.getType();
    public static final String JSON_TASK_LIST = "addTask";
    public static final String INDEX_TASK_MODIFIED = "modifiedTaskIndex";
    public static final String TASK_MODIFICATION = "modification";
    public static final String TASK_DETAIL = "task";
}
