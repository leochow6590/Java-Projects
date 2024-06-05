import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class is the server which runs the GUI with multi-thread.
 */
public class Server {
    ServerSocket socket_s;
    boolean gameover = false;
    int player_tag = 0;
    Field field = new Field();
    ArrayList<JButton> button_list = new ArrayList<JButton>();

    /**
     * Initiating the server
     * 
     * @param args
     */
    public static void main(String[] args){
        Server server = new Server();
        server.go();
    }

    /**
     * Utilizing multi-thread GUI to serves 2 clients.
     */
    public void go(){
        field.init();
        try{
            socket_s = new ServerSocket(5000);
            while (true){
                if (player_tag < 2){
                    Socket client = socket_s.accept();
                    player_tag++;
                    System.out.println("Player " + player_tag + " is connected");
                    ClientHandler clientSock = new ClientHandler(client, player_tag);
                    new Thread(clientSock).start();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Defining the runnable of threads
     */
    private class ClientHandler implements Runnable{
        Socket socket_c;
        boolean started = false;
        int player;
        String icon;

        /**
         * handle the client socket and its respective player number
         * 
         * @param socket
         * @param player_tag
         */
        public ClientHandler(Socket socket, int player_tag) {
            this.socket_c = socket;
            this.player = player_tag;
        }

        /**
         * This method handles all the operations of the game.
         */
        public void run(){
            if (player_tag == 1){
                icon = "X";
            } else {
                icon = "O";
            }
            JMenuBar menuBar = new JMenuBar();
            JMenu control_menu = new JMenu("Control");
            JMenu help_menu = new JMenu("Help");
            JMenuItem control_menu_item = new JMenuItem("Exit");
            JMenuItem help_menu_item = new JMenuItem("Instructions");
            control_menu.add(control_menu_item);
            help_menu.add(help_menu_item);
            menuBar.add(control_menu);
            menuBar.add(help_menu);
            JLabel info = new JLabel("Enter your player name...");
            JButton bttn_1 = new JButton(" ");
            button_list.add(bttn_1);
            JButton bttn_2 = new JButton(" ");
            button_list.add(bttn_2);
            JButton bttn_3 = new JButton(" ");
            button_list.add(bttn_3);
            JButton bttn_4 = new JButton(" ");
            button_list.add(bttn_4);
            JButton bttn_5 = new JButton(" ");
            button_list.add(bttn_5);
            JButton bttn_6 = new JButton(" ");
            button_list.add(bttn_6);
            JButton bttn_7 = new JButton(" ");
            button_list.add(bttn_7);
            JButton bttn_8 = new JButton(" ");
            button_list.add(bttn_8);
            JButton bttn_9 = new JButton(" ");
            button_list.add(bttn_9);
            JButton bttn_name = new JButton("Submit");
            JTextField txt_inputname = new JTextField(10);
            Popup help = new Popup();
            help.get_context(
                    "Some information about the game:\n"+"Criteria for a valid move:\n"+"-The move is not occupied by any mark.\n"+"-The move is made in the player's turn.\n"+
                    "-The move is made within the 3 x 3 board.\n"+"The game would continue and switch among the opposite player until it reaches either one of the following conditions:\n"+
                    "-Player 1 wins.\n"+"-Player 2 wins.\n"+"-Draw."
            );
            Popup empty_name = new Popup();
            empty_name.get_context(
                    "Please input name."
            );
            Popup one_player_left = new Popup();
            one_player_left.get_context("Game Ends. One of the players left.");
            Popup win = new Popup();
            win.get_context("Congratulations. You Win.");
            Popup lose = new Popup();
            lose.get_context("You lose.");
            Popup draw = new Popup();
            draw.get_context("Draw.");
            JPanel MainPanel = new JPanel();
            MainPanel.setLayout(new GridBagLayout());
            JPanel NamePanel = new JPanel();
            NamePanel.add(txt_inputname);
            NamePanel.add(bttn_name);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1; // default value
            c.gridheight = 1; // default value
            c.weightx = 0.0; // default value
            c.weighty = 0.0; // default value
            c.anchor = GridBagConstraints.CENTER; // default value
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(0, 0, 0, 0); // default value
            c.ipadx = 0; // default value
            c.ipady = 0; // default value
            c.weighty = 0.0;
            c.gridwidth = 3;
            c.weightx = 0.0;
            MainPanel.add(info, c);
            c.gridwidth = 1;
            c.weighty = 0.0;
            c.gridy = 1;
            c.weightx = 0.5;
            c.gridx = 0;
            c.ipady = 50;
            MainPanel.add(bttn_1, c);
            c.gridx = 1;
            MainPanel.add(bttn_2, c);
            c.gridx = 2;
            MainPanel.add(bttn_3, c);
            c.gridy = 2;
            c.gridx = 0;
            MainPanel.add(bttn_4, c);
            c.gridx = 1;
            MainPanel.add(bttn_5, c);
            c.gridx = 2;
            MainPanel.add(bttn_6, c);
            c.gridy = 3;
            c.gridx = 0;
            MainPanel.add(bttn_7, c);
            c.gridx = 1;
            MainPanel.add(bttn_8, c);
            c.gridx = 2;
            MainPanel.add(bttn_9, c);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(MainPanel);
            frame.setTitle("Tic Tac Toe");
            frame.setJMenuBar(menuBar);
            frame.add(NamePanel, BorderLayout.SOUTH);
            frame.setSize(400, 400);
            frame.setVisible(true);
            
            frame.addWindowListener(new WindowAdapter()
            {
                @Override
                /**
                 * Event listener for closing the window.
                 */
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("Player " + player + " disconnected");
                    e.getWindow().dispose();
                    field.left(player);
                }
            });
            control_menu_item.addActionListener(new ActionListener() {
                /**
                 * Event listener for "exit" button.
                 * 
                 * @param e
                 */
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            help_menu_item.addActionListener(new ActionListener() {
                /**
                 * Event listener for "help" button
                 * 
                 * @param e
                 */
                public void actionPerformed(ActionEvent e) {
                    help.show_context();
                }
            });

            
            for (int i = 0; i < button_list.size(); i ++){
            	button_list.get(i).setEnabled(false);
            }
            field.switch_player();
            bttn_name.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for name input.
                 */
                public void actionPerformed(ActionEvent e) {
                    if (txt_inputname.getText().trim().isEmpty()){
                        empty_name.show_context();
                    }
                    else{
                        String player_name = txt_inputname.getText();
                        txt_inputname.setEnabled(false);
                        bttn_name.setEnabled(false);
                        info.setText("WELCOME " + player_name);
                        bttn_1.setEnabled(true);
                        bttn_2.setEnabled(true);
                        bttn_3.setEnabled(true);
                        bttn_4.setEnabled(true);
                        bttn_5.setEnabled(true);
                        bttn_6.setEnabled(true);
                        bttn_7.setEnabled(true);
                        bttn_8.setEnabled(true);
                        bttn_9.setEnabled(true);
                        frame.setTitle("Tic Tac Toe-Player:" + player_name);

                    }

                }
            });

            bttn_1.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 1
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(0, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_2.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 2
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(1, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_3.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 3
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(2, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_4.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 4
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(3, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_5.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 5
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(4, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_6.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 6
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(5, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_7.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 7
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(6, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_8.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 8
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(7, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            bttn_9.addActionListener(new ActionListener() {
                @Override
                /**
                 * Event listener for button 9
                 */
                public void actionPerformed(ActionEvent e) {
                    if (field.now_player() == player){
                        field.move(8, player);
                        field.switch_player();
                        started = true;
                    }
                }
            });
            
            int result;
            boolean close_game = false;
            while (!gameover){
                result = field.check_result();
                if (result!=0){
                    if (result == 1){
                        if (player == result){
                            win.show_context();
                        }
                        else {
                            lose.show_context();
                        }
                    }
                    else if (result == 2){
                        if (player == result){
                            win.show_context();
                        }
                        else {
                            lose.show_context();
                        }
                    }
                    else {
                        draw.show_context();
                    }
                    close_game = true;
                }
                if (field.get(0)!=0){
                    bttn_1.setText(field.show(0));
                    bttn_1.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(1)!=0){
                    bttn_2.setText(field.show(1));
                    bttn_2.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(2)!=0){
                    bttn_3.setText(field.show(2));
                    bttn_3.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(3)!=0){
                    bttn_4.setText(field.show(3));
                    bttn_4.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(4)!=0){
                    bttn_5.setText(field.show(4));
                    bttn_5.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(5)!=0){
                    bttn_6.setText(field.show(5));
                    bttn_6.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(6)!=0){
                    bttn_7.setText(field.show(6));
                    bttn_7.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(7)!=0){
                    bttn_8.setText(field.show(7));
                    bttn_8.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (field.get(8)!=0){
                    bttn_9.setText(field.show(8));
                    bttn_9.setEnabled(false);
                    if (close_game){
                        gameover = true;
                    }
                }
                if (started){
                    if (field.now_player() == player){
                        info.setText("Your opponent has moved, now is your turn.");
                    }
                    else{
                        info.setText("Valid move, wait for your opponent.");
                        field.add_start();
                    }
                }
                if ( field.started() == 1){
                    info.setText("Your opponent has moved, now is your turn.");
                    field.add_start();
                }
                if (field.disconnected()){
                    if (player != field.get_left_player()){
                    	one_player_left.show_context();
                    }
                    for (JButton jButton : button_list) {
                        jButton.setEnabled(false);
                    }
                    gameover = true;
                }
            }
            System.out.println("Restart the server to replay.");
            for (int i = 0; i < button_list.size(); i ++){
                button_list.get(i).setEnabled(false);
            }
        }
    }

}
