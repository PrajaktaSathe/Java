# Espionage

## The Game
This is a boardgame roughly based on the Rock Paper Scissor idea.  
The more SecretCards you can capture, the faster you will move forward on the board.
  
  
Each round consist of three phases:  
* All the players are divided into two groups.  
  
    
* Group 1 fight each other  to get one of the two displayed secret cards on the board.  
The player using the highest bribe will get the card.  
An agent can steal this bribe - but only if it is the only agent in the group.  
    
    
* Group 2 fight each other to make the most progress on the board (and to steal SecretCards from each other!).  
If you have enough SecretCards you can choose to Report.  
The players with the two best Reports will move forward on the board. But beware...   
the Agents will steal your cards if there is not a Counter-espionage present.   
If there IS a counter-espionage, all the agents will fail and become prisoners   
and the counter-espionage players will move forward...  
  
  
## The Code
<table>   
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/Espionage.java">Espionage</a></td>
	<td>Program main w GUI Frame and Menu </td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/Player.java">Player</a></td>
	<td>Keeps track of the player's hand of cards and the current position on the board.</td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/Battle.java">Battle</a></td>
	<td>Holds the Game Logic</td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/Board.java">Board</a></td>
	<td>Consists mainly of a paint method that draws the board and the Player's hand of cards.</td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/ActionCard.java">ActionCard</a></td>
	<td>Each player has a set of ActionCards used to select next action.</td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/SecretCard.java">SecretCard</a></td>
	<td>This is the wanted treasure!</td>
</tr>
<tr>
	<td><a href="https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/ReportUtil.java">ReportUtil</a></td>
	<td>Utility to get all possible Reports from the player's hand of SecretCards - returns the best one.</td>
</tr>
</table>

## A Screenshot

![Espionage](https://github.com/PrajaktaSathe/Java/blob/main/Programs/Games/Espionage/Espionage.png)
