import javax.swing.*;

/**
 * Constructing the pop up message window.
 */
public class Popup {
    private String context = "";

    /**
     * Setting the context that would be shown in the pop up message window.
     * 
     * @param context
     */
    public  void get_context(String context){
        this.context = context;
    }

    /**
     * Showing the message window.
     */
    public void show_context()
    {
        JOptionPane.showMessageDialog(null, this.context, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
