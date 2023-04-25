## Intuitive configuration API for Spigot 
Have you ever needed a _smooth_ config API when making your plugins? Have you ever thought the existing spigot API was too _clunky_? Well, this is the API for you!

### Features
- [x] Easy to use
- [x] DataAdapters to load and save your own data
- [x] Configuration manager to manage your configs
- [x] Specify defaults for your configs from resources

### Setup
1. Add the jitpack repository to your pom.xml
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url> <!-- Add this repository -->
    </repository>
</repositories>
```

2. Add the dependency to your pom.xml
```xml
<dependencies>
    <dependency>
        <groupId>com.github.PartlySunnyDev</groupId>
        <artifactId>Configurate</artifactId>
        <version>-SNAPSHOT</version> <!-- Add this dependency -->
    </dependency>
</dependencies>
```

3. Shade the dependency into your plugin
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId> <!-- Add this plugin -->
            <version>3.2.4</version>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>me.partlysunny.configurate</pattern>
                        <shadedPattern>your.plugin.package.configurate</shadedPattern> <!-- Add this relocation -->
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Usage

### MAKE SURE TO INITIALIZE CONFIGURATE BEFORE USING IT
```java
// Initialize Configurate
Configurate configurate = new Configurate(this);
```

#### Simple config
```java
// Create a config
Config config = new SpigotConfig("myConfig.yml");
// Write some data to the config
config.set("myKey", "myValue");
config.set("myOtherKey", new Location(0, 0, 0), Adapters.LOCATION);
// Save the config
config.save();
// Register the config into manager
Configurate.instance().configManager().addConfig("myConfig", config);
```

#### Config with defaults
```java
Config config = new SpigotConfig("myConfig.yml", "myConfig.yml");
config.saveDefaults();
// Assuming there is a myConfig.yml in your resource path, it will be copied to your plugin's data folder
// There is no requirement to save the config into the manager, but the manager will allow you to saveAll() etc.
```