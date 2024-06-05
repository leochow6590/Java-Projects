import java.util.ArrayList;

/**
 * This class handles synchronized variables.
 */
public class Field {
    ArrayList<Integer> field = new ArrayList<Integer>();
    int current_player = 1;
    int player_number = 2;
    int left_player;
    int start = 0;

    /**
     * Returning the player in current thread
     * 
     * @return current_player
     */
    public synchronized int now_player(){
        return current_player;
    }
    
    /**
     * Finding the player who left
     * 
     * @param player
     */
    public synchronized void left(int player){
        player_number--;
        left_player = player;
    }
    
    /**
     * Returning the player who left
     *
     * @return left_player
     */
    public synchronized int get_left_player(){
        return left_player;
    }

    /**
     * Returning if any player has left
     * 
     * @return true/false
     */
    public synchronized boolean disconnected(){
        if (player_number == 2){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Switching player's turn
     * 
     * @return current player
     */
    public synchronized int switch_player(){
        if (current_player == 1){
            current_player = 2;
            return current_player;
        }
        else{
            current_player = 1;
            return current_player;
        }
    }

    /**
     * Initializing the game field
     */
    public synchronized void init(){
        for (int i = 0; i < 9; i++){
            field.add(0);
        }
    }

    /**
     * Returning the game field
     *
     * @return
     */
    public synchronized ArrayList<Integer> show_field(){
        return field;
    }

    /**
     * Return if anyone won. 1: 1 won, 2: 2 won, 3: draw.
     * 
     * @return 1,2,3
     */
    public synchronized int check_result(){
        if (field.get(0) == 1 && field.get(1) == 1 && field.get(2) == 1){
            return 1;
        }
        else if (field.get(0) == 2 && field.get(1) == 2 && field.get(2) == 2){
            return 2;
        }
        else if (field.get(3) == 1 && field.get(4) == 1 && field.get(5) == 1){
            return 1;
        }
        else if (field.get(3) == 2 && field.get(4) == 2 && field.get(5) == 2){
            return 2;
        }
        else if (field.get(6) == 1 && field.get(7) == 1 && field.get(8) == 1){
            return 1;
        }
        else if (field.get(6) == 2 && field.get(7) == 2 && field.get(8) == 2){
            return 2;
        }
        else if (field.get(0) == 2 && field.get(3) == 2 && field.get(6) == 2){
            return 2;
        }
        else if (field.get(0) == 1 && field.get(3) == 1 && field.get(6) == 1){
            return 1;
        }
        else if (field.get(1) == 2 && field.get(4) == 2 && field.get(7) == 2){
            return 2;
        }
        else if (field.get(1) == 1 && field.get(4) == 1 && field.get(7) == 1){
            return 1;
        }
        else if (field.get(2) == 2 && field.get(5) == 2 && field.get(8) == 2){
            return 2;
        }
        else if (field.get(2) == 1 && field.get(5) == 1 && field.get(8) == 1){
            return 1;
        }
        else if (field.get(0) == 2 && field.get(4) == 2 && field.get(8) == 2){
            return 2;
        }
        else if (field.get(0) == 1 && field.get(4) == 1 && field.get(8) == 1){
            return 1;
        }
        else if (field.get(2) == 2 && field.get(4) == 2 && field.get(6) == 2){
            return 2;
        }
        else if (field.get(2) == 1 && field.get(4) == 1 && field.get(6) == 1){
            return 1;
        }
        else if (!field.contains(0)){
            return 3;
        }
        else{
            return 0;
        }

    }

    /**
     * Recognize the moves.
     * 
     * @param index
     * @param player_num
     */
    public synchronized void move(int index, int player_num){
        field.set(index, player_num);
    }

    /**
     * Returning the icon corresponds to the 2 players.
     * 
     * @param index
     * @return "X", "O", ""
     */
    public synchronized String show(int index){
        if (field.get(index)==1){
            return "X";
        }
        else if (field.get(index)==2){
            return "O";
        }
        else{
            return "";
        }
    }

    /**
     * Returning the icon already on the field
     * 
     * @param index
     * @return icon that on the field
     */
    public synchronized int get(int index){
        return field.get(index);
    }

    /**
     * Returning that the game has been started.
     * 
     * @return start
     */
    public synchronized int started(){
        return start;
    }

    /**
     * Changing the value of start to move out from the initial state.
     */
    public synchronized void add_start(){
        start++;
    }
}
