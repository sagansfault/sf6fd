# sf6fd
A very simple library written to scrape and represent framedata and other info of Street Fighter 6  
Example - Getting Marisa's highest damage move:
```java
FrameData frameData = FrameData.load().get(); // sf6fd is async so handle this accordingly
Character marisa = frameData.getCharacter(CharacterId.MARISA).get(); // an optional for safety
Optional<Move> highest = marisa.getMoves().stream()
    .max(Comparator.comparingInt(move -> {
        try {
            return Integer.parseInt(move.damage());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }));
highest.ifPresent(move -> System.out.println("Marisa's highest damage move is '" + move.name() + "' at: " + move.damage()));
```
You may note that this is not, in fact, Marisa's highest damage move. That is because the higher damage moves are listed
differently on the frame data source site and are represented raw, as the same, in this api for respect to premature
data mutation. For this example they would be listed as: `2900[2640]` But this does not parse cleanly to an integer. You will
have to do your own custom comparison as this library will not omit data.   

Install it to your local maven repository and depend on it with 
### Gradle
```groovy
dependencies {
    implementation 'sh.sagan:sf6fd:0.1.0' // Check for most recent version!
}
```
### Maven
```xml
<dependency>
    <groupId>sh.sagan</groupId>
    <artifactId>sf6fd</artifactId>
    <!-- Check for the most recent version! -->
    <version>0.1.0</version>
</dependency>
```