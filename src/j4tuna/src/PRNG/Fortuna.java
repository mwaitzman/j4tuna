package PRNG;

public class Fortuna {
    private Generator G;
    private String key;
    private int counter;
    private String[31] Pool;
    private int reseedCount;
    private void initializeGenerator() {
        this.key = 0;
        this.counter = 0;
    }
    private void reseed(String seed) {
        this.key = SHA256(this.key + seed);
        this.counter += 1;
    }
    private String generateBlocks(int numberOfBlocks) {
        assert this.counter != 0;
        String r = "";
        for(int i=0; i <= numberOfBlocks; i++) {
            r += Encrypt(this.key, this.counter);
        }
        return r;
    }
    public String pseudoRandomData(int n) {
        assert 0 <= n <= Math.pow(2,20);
        String r = generateBlocks(Math.ceil(n/16));
        this.key = generateBlocks(2);
        return r;
    }
    private void initializePRNG() {
        for(int i=0; i <= 31; i++) {
            Pool[i] = "";
        }
        this.reseedCount = 0;
        this.
    }
    private String randomData(int n) {
        if (Pool[0].length() > MinPoolSize & last reseed > 100 ms ago) {//TODO implement the latter part of the if (probably using (current time in unix time - a variable that will save the time of the last reseed) >= 100)
            reseedCount += 1;
            seed = "";
            for (int i = 0; i =< 31; i++) {
                if (Math.pow(2,i) % reseedCount == 0) {
                    seed += SHA256(Pool[i]);
                    Pool[i] = "";
                }
            }
            reseed(seed);
        }
        if (reseedCount == 0) {
            throw new Error("PRNG not seeded yet");
        }
        else {
            return pseudoRandomData(n);
        }
    }
    public void addRandomEvent(short sourceNumber, short poolNumber, String data) {
        assert 1 <= data.length() <= 32 & 0 <= sourceNumber <= 255 & 0 <= poolNumber <= 31;
        Pool[poolNumber] += sourceNumber + data.length() + data;
    }
    private void writeSeedFile(File targetFile) {
        //TODO write to the file (randomData(64));
    }
    private void UpdateSeedFile(File targetFile) {//TODO change it so the the seed file is an objectin the class, making this function and the previous one have an empty method signature
        String s = targetFile.read(targetFile);//TODO make this work lol
        assert s.length() = 64;
        reseed(s);
        targetFile.write(randomData(64));
    }
}
