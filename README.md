# Tic-Tac-Toe-With-AI
// TODO: currently implementing 'hard' difficulty with minimax algorithm

how to play:
'start' followed by players
easy - easy bot
medium - medium bot
user - user
exit - quit

'start user user', 'start medium user' or however

This Java project involves the creation of a Tic-Tac-Toe game with an AI opponent that operates at different difficulty levels. The AI employs a strategy where it maps out the taken tiles on the board to optimize memory usage and processing speed. Turns are efficiently swapped between the AI and the user.

The project successfully manages coordinates on the board, updating them with each move to help the AI make informed decisions. This list of coordinates aids the AI in selecting its moves strategically, preventing it from getting stuck in a loop and conserving resources. Since the AI selects available coordinates from the mapped list, there's no need to validate its moves, making the game more foolproof for the AI, while still allowing user input.

At the hard difficulty level, the AI employs a more advanced strategy. Instead of just looking one move ahead for an immediate win or loss, it can look several moves ahead, calculating all possible game scenarios. This allows the AI to make optimal moves, assuming that its opponent plays perfectly, ensuring a flawless performance regardless of the opponent's skill level.

The key algorithm used in this project is called "minimax," a recursive brute force algorithm that maximizes the AI's position's value while minimizing its opponent's worth. Minimax can be applied not only to Tic-Tac-Toe but also to other games with alternating player moves, such as chess.
