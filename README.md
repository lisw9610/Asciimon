# Asciimon
## Team Members
- Liam Sweeney
- Nguyen Pham

## Language
Java

## Platform
Spring Boot

## Description
Asciimon is a simple online card-based fighting game inspired by games such as *Magic: The Gathering* and *Pokémon*. Players battle opponents using cards from their own deck. Each card has an elemental type, and matchups include elemental advantages and disadvantages, causing attacks to deal increased or reduced damage depending on the types of the player’s and opponent’s cards.

The cards are displayed using ASCII art. The user interface is a simple HTML-based web app that allows players to queue for matches, select cards, and choose actions during battle.

## Tentative Features
- **Gacha system**  
  Players can draw new cards using currency earned from winning matches. This requires a database to store player cards, currency, and other related records.

- **Card progression**  
  Cards gain experience from being used in matches, allowing them to level up and improve their stats. This requires defining XP requirements, level progression, and stat increases for each level, along with storing that data for each player.

- **Items in battle**  
  Players can use consumable items such as potions and temporary buff items during matches.

- **Ranking-based matchmaking**  
  Players are matched with others of similar skill levels to improve fairness and encourage balanced competition.