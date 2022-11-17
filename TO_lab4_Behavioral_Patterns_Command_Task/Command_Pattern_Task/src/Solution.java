import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {


    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public static class Application {
        Scanner scanner;
        private Editor editor;
        private CommandHistory history;


        public void run() {

            editor = new Editor();
            history = new CommandHistory();
            scanner = new Scanner(System.in);

            while(true)
            {
                String inputLine = scanner.nextLine();

                String parts[] = inputLine.split(" ",2);
                System.out.println(parts[0]);
                System.out.println(parts[1]);


                String method = parts[0];
                String params = parts[1];


                switch (method)
                {
                    case "C":
                        CommandCreate command = new CommandCreate();
                        command.execute(params, editor);
                        history.push(command);
                        break;

                    default:
                }

                System.out.println(editor.getSquares());
            }
        }
    }

    public static class Editor {
        private List<Square> squares = new ArrayList<>();

        public List<Square> getSquares() {
            return squares;
        }

        public void setSquares(List<Square> squares) {
            this.squares = squares;
        }

        public void addSquare(Square sqr) {
            squares.add(sqr);
        }
    }

    public static abstract class Command {
        public void execute(String params, Editor editor){}
    }


    public static class CommandHistory {
        private List<Command> commands = new ArrayList<>();

        public void push(Command c) {
            commands.add(c);
        }

        public void pop() {
            commands.remove(commands.lastIndexOf(this));
        }

    }

    public static class CommandCreate extends Command{
        @Override
        public void execute(String params, Editor editor) {

            String paramsArray[] = params.split(" ");

            int SquareID = Integer.parseInt(paramsArray[0]);

            int sideLength = Integer.parseInt(paramsArray[1]);

            editor.addSquare(new Square(SquareID, sideLength));

        }
    }





    public static class Square {
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
    }

}

