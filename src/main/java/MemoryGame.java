import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet("/MemoryGame")
public class MemoryGame extends HttpServlet {
    private static final int GRID_SIZE = 4; // 4x4 grid
    private static final String[] CARD_VALUES = {
            "A", "B", "C", "D", "E", "F", "G", "H"
    };
    private static List<String> cards;

    static {
        // Initialize the card deck by duplicating and shuffling values
        cards = new ArrayList<>();
        for (String value : CARD_VALUES) {
            cards.add(value);
            cards.add(value);
        }
        Collections.shuffle(cards);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("gameGrid") == null) {
            initializeGame(session);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String[][] gameGrid = (String[][]) session.getAttribute("gameGrid");
        boolean[][] revealed = (boolean[][]) session.getAttribute("revealed");

        out.println("<html><head><title>Memory Game</title></head><body>");
        out.println("<h1>Memory Game</h1>");
        out.println("<form method='post'>");

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (revealed[i][j]) {
                    out.printf("<button disabled>%s</button>", gameGrid[i][j]);
                } else {
                    out.printf("<button name='cell' value='%d,%d'>?</button>", i, j);
                }
            }
            out.println("<br>");
        }

        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String cell = request.getParameter("cell");
        if (cell != null) {
            HttpSession session = request.getSession();
            String[][] gameGrid = (String[][]) session.getAttribute("gameGrid");
            boolean[][] revealed = (boolean[][]) session.getAttribute("revealed");
            String[] coordinates = cell.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            revealed[x][y] = true;

            // Additional game logic for checking matches can be added here.
        }
        doGet(request, response);
    }

    private void initializeGame(HttpSession session) {
        String[][] gameGrid = new String[GRID_SIZE][GRID_SIZE];
        boolean[][] revealed = new boolean[GRID_SIZE][GRID_SIZE];
        Iterator<String> cardIterator = cards.iterator();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gameGrid[i][j] = cardIterator.next();
                revealed[i][j] = false;
            }
        }

        session.setAttribute("gameGrid", gameGrid);
        session.setAttribute("revealed", revealed);
    }
}
