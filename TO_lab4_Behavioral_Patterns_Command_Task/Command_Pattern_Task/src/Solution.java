import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}

class Application {
    Scanner scanner;
    private Editor editor;
    private CommandHistory history;

    public void run() {

        history = new CommandHistory();
        editor = new Editor(history);
        scanner = new Scanner(System.in);
        String params;

        while(true)
        {
            String inputLine = scanner.nextLine();

            String parts[] = inputLine.split(" ",2);

            String method = parts[0];

            switch (method)
            {
                case "C":
                    params = parts[1];
                    CommandCreate commandC = new CommandCreate();
                    commandC.execute(params, editor, method);
                    history.push(commandC);
                    break;

                case "P":
                    CommandPrint commandP = new CommandPrint();
                    commandP.execute(null, editor, method);
                    break;

                case "M":
                    params = parts[1];
                    CommandMove commandM = new CommandMove();
                    commandM.execute(params, editor, method);
                    history.push(commandM);
                    break;

                case "S":
                    params = parts[1];
                    CommandScale commandS = new CommandScale();
                    commandS.execute(params, editor, method);
                    history.push(commandS);
                    break;

                case "U":
                    CommandUndo commandU = new CommandUndo();
                    commandU.execute(null, editor, method);
                    break;

                case "R":
                    CommandRedo commandR = new CommandRedo();
                    commandR.execute(null, editor, method);
                    break;

                default:
                    break;
            }
        }
    }
}

class Editor {
    private List<Square> squares = new ArrayList<>();
    private CommandHistory history;

    public Editor(CommandHistory history) {
        this.history = history;
    }
    public List<Square> getSquares() {
        return squares;
    }

    public Square getSquare(int id) {
        for (int i = 0; i < squares.size(); i++ ) {
            if(squares.get(i).getIdNumber() == id)
            {
                return squares.get(i);
            }
        }
        return null;
    }

    public void addSquare(Square sqr) {
        squares.add(sqr);
    }

    public void replaceSquare(Square square) {
        for (int i = 0; i < squares.size(); i++ ) {
            if(squares.get(i).getIdNumber() == square.getIdNumber())
            {
                squares.set(i, square);
                break;
            }
        }
    }

    public void removeSquare(int id) {
        if(squares.size() > 0)
        {
            for (int i = 0; i < squares.size(); i++ ) {
                if(squares.get(i).getIdNumber() == id)
                {
                    squares.remove(i);
                    break;
                }
            }
        }
    }
    public CommandHistory getCommandHistory() {
        return history;
    }
}


class CommandHistory {
    private List<Command> commandsList = new ArrayList<>();
    private List<Command> undoneCommandsList = new ArrayList<>();
    public void push(Command c) {
        commandsList.add(c);
    }

    public Command pop() {

        if(commandsList.size() > 0) {

            undoneCommandsList.add(commandsList.get(commandsList.size() - 1));

            commandsList.remove(commandsList.get(commandsList.size() - 1));

            return undoneCommandsList.get(undoneCommandsList.size() - 1);
        }

        return null;
    }

    public Command popUndoneCommand() {
        if(undoneCommandsList.size() > 0)
        {
            commandsList.add(undoneCommandsList.get(undoneCommandsList.size() -1 ));

            undoneCommandsList.remove(undoneCommandsList.get(undoneCommandsList.size() -1 ));

            return commandsList.get(commandsList.size() - 1);
        }

        return null;
    }

    public void removeLastCommand() {

        if(commandsList.size() > 0)
            commandsList.remove(commandsList.get(commandsList.size() - 1));
    }
}

abstract class Command {

    private String method;
    public void execute(String params, Editor editor, String method){}

    public String getMethod() {
        return method;
    }
}

class CommandCreate extends Command{

    private String method;
    private int squareID;
    private int sideLength;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        squareID = Integer.parseInt(paramsArray[0]);

        sideLength = Integer.parseInt(paramsArray[1]);

        boolean squareExisted = false;

        for (Square sqr: editor.getSquares()) {
            if(sqr.getIdNumber() == squareID)
            {
                editor.replaceSquare(new Square(squareID, sideLength));
                squareExisted = true;
                break;
            }
        }

        if(!squareExisted)
            editor.addSquare(new Square(squareID, sideLength));

    }


    public int getSquareID() {
        return squareID;
    }

    public void setSquareID(int squareID) {
        squareID = squareID;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public String getMethod() {
        return method;
    }

    public String getSquareIDtoString() {
        return new String(String.valueOf(squareID));
    }

    public String getSideLengthToString() {
        return new String(String.valueOf(sideLength));
    }

}

class CommandPrint extends Command{
    private List<Square> squares = new ArrayList<>();
    @Override
    public void execute(String params, Editor editor, String method) {
        squares = editor.getSquares();

        Collections.sort(squares);

        for (Square sqr: squares) {
            System.out.println(sqr.getIdNumber() + " " + sqr.getCenterCoordinateX() + " " + sqr.getCenterCoordinateY() + " " + sqr.getSideLength());
        }
    }
}

class CommandMove extends Command{

    private String method;

    int squareID;
    int x;

    int y;

    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        squareID = Integer.parseInt(paramsArray[0]);

        x = Integer.parseInt(paramsArray[1]);

        y = Integer.parseInt(paramsArray[2]);


        if(editor.getSquare(squareID) != null) {

            x = x + editor.getSquare(squareID).getCenterCoordinateX();

            editor.getSquare(squareID).setCenterCoordinateX(x);

            y = y + editor.getSquare(squareID).getCenterCoordinateY();

            editor.getSquare(squareID).setCenterCoordinateY(y);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getX() {
        return new String(String.valueOf(x));
    }

    public String getY() {
        return new String(String.valueOf(y));
    }

    public String getSquareID() {
        return new String(String.valueOf(squareID));
    }
}

class CommandScale extends Command{

    private String method;

    int squareID;

    int scale;
    @Override
    public void execute(String params, Editor editor,String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        squareID = Integer.parseInt(paramsArray[0]);

        scale = Integer.parseInt(paramsArray[1]);


        if(editor.getSquare(squareID) != null) {

            int length = scale * editor.getSquare(squareID).getSideLength();

            editor.getSquare(squareID).setSideLength(length);

        }
    }

    public String getMethod() {
        return method;
    }

    public String getScale() {
        return new String(String.valueOf(scale));
    }

    public String getSquareID() {
        return new String(String.valueOf(squareID));
    }
}

class CommandUndo extends Command{

    private String method;
    CommandHistory history;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        history = editor.getCommandHistory();

        Command c = history.pop();

        if(c != null)
        {
            method = c.getMethod();
        }
        else
            method = "X";



        if(method.equals("C")) {

            CommandUndoC undoC = new  CommandUndoC();

            String id = ((CommandCreate) c).getSquareIDtoString();


            undoC.execute(id, editor, method);
        }

        if(method.equals("M")) {

            CommandUndoM undoM = new  CommandUndoM();

            String x = ((CommandMove) c).getX();

            String y = ((CommandMove) c).getY();

            String id = ((CommandMove) c).getSquareID();

            String parameters = id + " " + x + " " + y;

            undoM.execute(parameters, editor, method);
        }

        if(method.equals("S")) {

            CommandUndoS undoS = new  CommandUndoS();

            String s = ((CommandScale) c).getScale();

            String id = ((CommandScale) c).getSquareID();

            String parameters = id + " " + s;

            undoS.execute(parameters, editor, method);
        }
    }
}

class CommandUndoC extends Command {

    private String method;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        int id = Integer.parseInt(paramsArray[0]);

        editor.removeSquare(id);
    }
}

class CommandUndoM extends Command {

    private String method;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        int id = Integer.parseInt(paramsArray[0]);

        int x = Integer.parseInt(paramsArray[1]);

        int y = Integer.parseInt(paramsArray[2]);

        if(editor.getSquare(id) != null) {

            editor.getSquare(id).setCenterCoordinateX(editor.getSquare(id).getCenterCoordinateX() - x);

            editor.getSquare(id).setCenterCoordinateY(editor.getSquare(id).getCenterCoordinateY() - y);
        }
    }
}

class CommandUndoS extends Command {

    private String method;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        String paramsArray[] = params.split(" ");

        int id = Integer.parseInt(paramsArray[0]);

        int s = Integer.parseInt(paramsArray[1]);

        if(editor.getSquare(id) != null) {

            editor.getSquare(id).setSideLength(editor.getSquare(id).getSideLength() / s);
        }
    }
}


class CommandRedo extends Command{

    private String method;
    CommandHistory history;
    @Override
    public void execute(String params, Editor editor, String method) {

        this.method = method;

        history = editor.getCommandHistory();

        Command c = history.popUndoneCommand();

        if(c != null)
        {
            method = c.getMethod();
        }
        else
            method = "X";



        if(method.equals("C")) {


            String id = ((CommandCreate) c).getSquareIDtoString();

            String sideLength = ((CommandCreate) c).getSideLengthToString();

            history.removeLastCommand();

            CommandCreate redoC = new  CommandCreate();

            String parameters = id + " " + sideLength;

            redoC.execute(parameters, editor, method);

            history.push(redoC);

        }

        if(method.equals("M")) {

            String x = ((CommandMove) c).getX();

            String y = ((CommandMove) c).getY();

            String id = ((CommandMove) c).getSquareID();

            history.removeLastCommand();

            CommandMove redoM = new CommandMove();

            String parameters = id + " " + x + " " + y;

            redoM.execute(parameters, editor, method);

            history.push(redoM);

        }

        if(method.equals("S")) {

            String s = ((CommandScale) c).getScale();

            String id = ((CommandScale) c).getSquareID();

            history.removeLastCommand();

            CommandScale redoS = new CommandScale();

            String parameters = id + " " + s;

            redoS.execute(parameters, editor, method);

            history.push(redoS);
        }
    }
}

class Square implements Comparable<Square>{
    private int idNumber;
    private int sideLength;
    private int centerCoordinateX;
    private int centerCoordinateY;

    Square(int id, int side){
        idNumber = id;
        sideLength = side;
        centerCoordinateX = 0;
        centerCoordinateY = 0;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public int getCenterCoordinateX() {
        return centerCoordinateX;
    }

    public void setCenterCoordinateX(int centerCoordinateX) {
        this.centerCoordinateX = centerCoordinateX;
    }

    public int getCenterCoordinateY() {
        return centerCoordinateY;
    }

    public void setCenterCoordinateY(int centerCoordinateY) {
        this.centerCoordinateY = centerCoordinateY;
    }

    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "Square{" +
                "idNumber=" + idNumber +
                ", sideLength=" + sideLength +
                ", centerCoordinateX=" + centerCoordinateX +
                ", centerCoordinateY=" + centerCoordinateY +
                '}';
    }

    @Override
    public int compareTo(Square o) {
        int compareID = o.getIdNumber();

        //  For Ascending order:
        return this.idNumber - compareID;

        // For Descending order:
        // return compareID-this.idNumber;
    }
}
