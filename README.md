# Emote Wheel

A Fortnite-style emote wheel for Minecraft **1.21.11 (Fabric)** with **185 emotes and poses**, a customizable wheel,
favorites, search, and rebindable keys. Separate from the Hinata/Maid armor mod.

## Controls (all rebindable in *Options > Controls > Key Binds > Emote Wheel*)

| Default | Action |
|---|---|
| **B** (hold) | Open the wheel. Move the mouse toward a slice and **let go to play it**. Tap instead, and click a slice. |
| **N** | Open the full emote menu / wheel editor |
| *unbound* | Stop emote, plus **Quick Emote: Slot 1-8** (plays that slot of your current page) |

On the wheel: **1 / 2 / 3** or the **mouse wheel** switch pages (3 pages x 8 slots = 24 slots).
**Right-click** a slice to edit it. Clicking an **empty** slice jumps to the editor. **E** opens the full menu.

## The editor (N)
- Search, filter by category, or show only your **favorites**.
- Pick a wheel slot on the right, then **click emotes** to fill it (it moves to the next slot automatically).
- Select an emote and press **Play** to try it, **Favorite** to star it, **Clear Slot** to empty a slot.
- Options: stop emote when you move, switch to third person while emoting, show/hide other players' emotes.
- Everything is saved to `config/emote_wheel.json`.

Playing the emote you're already doing stops it. Emotes also stop if you move, jump, sneak, attack or get hurt (toggle in the menu).

## Can other people see my emotes?
Yes, if **the server and the other players also have this mod**. The server just relays "player X started emote Y" to
nearby players; it doesn't need to know anything else. On a server without the mod (or in a world where you're the only
one with it), emotes still work but only **you** see them. Players without the mod see nothing at all.

## Emotes
- **Greetings** (13): Wave, Big Wave, Hi There, Salute, Bow, Gentleman's Bow, Blow Kiss, Thumbs Up, High Five, Handshake, Come Here, Hello Everyone, Hey, You!
- **Poses** (35): T-Pose, A-Pose, Hands on Hips, Arms Crossed, Superhero, Thinker, Facepalm, Shrug, Pray, Zombie, Point Forward, To the Sky, Hands Up!, Flex, Victory, Peace Sign, Lunge, Hero Landing, Sit, Sit Cross-Legged, Meditate, Lounge, Cool Lean, Model Pose, At Attention, At Ease, Tiptoes, Crane Stance, Captain Pose, Dramatic Gaze, Flamingo, Fighting Stance, Lie Down, Hug Yourself, Hero Salute
- **Dances** (34): Disco, Night Fever, Floss, Dab, The Twist, Robot, Chicken Dance, Macarena, Letters (Y-M-C-A), Moonwalk, Running Man, Sprinkler, Shuffle, Headbang, Raise the Roof, Mix It Up, Windmill Arms, Helicopter, Slow Sway, Salsa, Hype Jump, Pogo, Bounce, Can-Can, Conductor, Air Guitar, Air Drums, Hula, Ballet Twirl, Cheerleader, Arm Ripple, Snap Along, Charleston, Side Step
- **Actions** (34): Jumping Jacks, Jump, Hop, Squats, Run in Place, Sprint, March, Kick, Punch, Shadow Boxing, Karate Chop, Sword Slash, Archer, Cast a Line, Chop Wood, Mining, Digging, Munch, Drink, Clap, Slow Clap, Standing Ovation, Fist Pump, Stretch, Side Stretch, Touch Your Toes, Push-Ups, Lie Face Down, Backflip, Front Flip, Cartwheel, Pirouette, Spin, Superman
- **Moods** (28): Laugh, Evil Laugh, Cry, Angry Stomp, Scared, Shiver, Nod (Yes), Shake Head (No), Confused, Sleepy, Yawn, Sigh, Exhausted, Dizzy, Proud, Shy, Sneaky, Look Around, Impatient, Surprised, Shocked, Worried, Bored, Disappointed, Love-Struck, Heartbroken, Sleeping, Standing Nap
- **Silly** (22): Penguin Waddle, Duck Walk, Bunny Hop, Frog Hop, Gorilla, T-Rex, Spooky Ghost, Jelly Wobble, Rubber Arms, Glitch, Flail, Earthquake, Selfie, Phone Call, Karaoke, Buffering..., Noodle Arms, Starfish, Scarecrow, Sloth Hang, Crab Walk, Runway Walk
- **Maid & Cat** (19): Maid Curtsy, Welcome Home, Master, Serve Tea, Sweep the Floor, Dust the Shelves, Wipe the Window, Nya~, Cat Pounce, Cat Stretch, Cat Loaf, Paw Wave, Heart Hands, Ear Wiggle, Please?, Cat Nap, Neko Dance, This Way, Master, Sparkle Pose, Yes, Master!

## Building
`./gradlew build` (Java 21+). The jar ends up in `build/libs/`. The included GitHub Action does this on every push.
Needs Minecraft 1.21.11, Fabric Loader 0.19.5+ and Fabric API.

## Adding your own emotes
Emotes are plain Java in `src/main/java/com/example/emotewheel/emote/`. Each one is a function from time to a pose:

```java
Emotes.add("my_wave", "My Wave", GREET, "poppy", 0, (p, t) -> {
    p.rax = -2.75f;                       // right arm up
    p.raz = -0.3f + sin(t * 9) * 0.4f;    // sway it
});
```
`duration` 0 = plays until cancelled; a number of seconds = one-shot. See `Pose.java` for every field
(arms, legs, head, body, `lean`, and whole-body `rootX/Y/Z`, `spin`, `flip`, `roll`).
