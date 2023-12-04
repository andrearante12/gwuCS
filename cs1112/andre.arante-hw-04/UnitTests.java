/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2023

Unit tests for both the array-list and linked-list based shopping catalogs.
For full credit, this application must generate zero errors.

authors: Charles Peeke
--------------------------------------------------------------------------*/
public class UnitTests {

    private static Song[] songs;

    public static void main(String[] args) {

        songs = CatalogHelper.getCatalog();

        boolean failed = false;

        // Validate the array-list implementation first
        System.out.println("BEGIN: Testing Array List based catalog");
        if(!testArrayList()) {
            failed = true;
            System.out.println("FAILED: The Array List based catalog failed unit testing");
        } else {
            System.out.println("PASSED: The Array List based catalog passed all unit tests");
        }

        
        // Validate the linked-list implementation second
        failed = false;
        System.out.println("BEGIN: Testing Linked List based catalog");

        if(!testLinkedList()) {
            failed = true;
            System.out.println("FAILED: The Linked List based catalog failed unit testing");
        } else {
            System.out.println("PASSED: The Linked List based catalog passed all unit tests");
        }

    }

    /// Carries out tests involving the array-list based catalog
    public static boolean testArrayList() {
        boolean success = true;

        if(!unittest1(new ArrayList())) {
            success = false;
        }
        if(!unittest2(new ArrayList())) {
            success = false;
        }
        if(!unittest3(new ArrayList())) {
            success = false;
        }
        if(!unittest4(new ArrayList())) {
            success = false;
        }
        if(!unittest5(new ArrayList())) {
            success = false;
        }
        if(!unittest6(new ArrayList())) {
            success = false;
        }
        if(!unittest7(new ArrayList())) {
            success = false;
        }
        return success;
    }

    /// Carries out tests involving the linked-list based catalog
    public static boolean testLinkedList() {
        boolean success = true;

        if(!unittest1(new LinkedList())) {
            success = false;
        }
        if(!unittest2(new LinkedList())) {
            success = false;
        }
        if(!unittest3(new LinkedList())) {
            success = false;
        }
        if(!unittest4(new LinkedList())) {
            success = false;
        }
        if(!unittest5(new LinkedList())) {
            success = false;
        }
        if(!unittest6(new LinkedList())) {
            success = false;
        }
        if(!unittest7(new LinkedList())) {
            success = false;
        }
        return success;
    }

    /// Validate methods involving an empty catalog
    public static boolean unittest1(MusicCatalog catalog) {
        boolean success = true;

        // Validate that a freshly made catalog is empty
        if(!catalog.isEmpty()) {
            System.out.println("FAILED: unittest1::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 0) {
            System.out.println("FAILED: unittest1::subtest 2::count==0 failed");
            success = false;
        }

        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest1::subtest 3::integrity check failed");
            success = false;
        }

        return success;
    }

    /// Validates adding and removing one item from the catalog
    public static boolean unittest2(MusicCatalog catalog) {
        boolean success = true;

        // Add a song into the catalog
        catalog.add(songs[4]);

        // Validate that the catalog is now non-empty and contains one song
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest2::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 1) {
            System.out.println("FAILED: unittest2::subtest 2::count==1 failed");
            success = false;
        }

        // Remove the one song from the catalog
        catalog.remove();

        // Validate that the catalog is now empty and contains no songs
        if(!catalog.isEmpty()) {
            System.out.println("FAILED: unittest2::subtest 3::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 0) {
            System.out.println("FAILED: unittest2::subtest 4::count==0 failed");
            success = false;
        }
        
        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest2::subtest 6::integrity check failed");
            success = false;
        }
        return success;
    }

    /// Validates adding, removing, and adding again to catalog
    public static boolean unittest3(MusicCatalog catalog) {
        boolean success = true;

        // add 1 song into the catalog
        catalog.add(songs[4]);

        // Validate the state of the catalog reflects that there is a
        // song in the catalog
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest3::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 1) {
            System.out.println("FAILED: unittest3::subtest 2::count==1 failed");
            success = false;
        }

        // Remove the song
        Song song = catalog.remove();

        // Validate the state of the catalog as empty
        if(!catalog.isEmpty()) {
            System.out.println("FAILED: unittest3::subtest 3::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 0) {
            System.out.println("FAILED: unittest3::subtest 4::count==0 failed");
            success = false;
        }
        // Validate that the removed song is the desired song
        if(song != songs[4]) {
            System.out.println("FAILED: unittest3::subtest 5::remove song validation failed");
            success = false;
        }

        // Add another (different) song into the catalog
        catalog.add(songs[5]);

        // Validate that the catalog now contains one song
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest3::subtest 6::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 1) {
            System.out.println("FAILED: unittest3::subtest 7::count==1 failed");
            success = false;
        }

        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest3::subtest 8::integrity check failed");
            success = false;
        }
        return success;
    }

    /// Validates search within a catalog
    public static boolean unittest4(MusicCatalog catalog) {
        boolean success = true;

        // add 3 songs into the catalog
        catalog.add(songs[4]);
        catalog.add(songs[5]);
        catalog.add(songs[6]);
        catalog.add(songs[7]);
        catalog.add(songs[8]);

        // Validate the state of the catalog reflects that there are 3
        // songs in the catalog
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest4::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 5) {
            System.out.println("FAILED: unittest4::subtest 2::count==5 failed");
            success = false;
        }

        boolean found;
        // Validate that contains locates a song in the catalog
        found = catalog.contains(songs[7]);
        if (!found) {
            System.out.println("FAILED: unittest4::subtest 3::contains failed");
            success = false;
        }

        // Validate the state of the catalog did not change
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest4::subtest 4::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 5) {
            System.out.println("FAILED: unittest4::subtest 5::count==5 failed");
            success = false;
        }
        
        // Validate that contains fails to find a song not in the catalog
        found = catalog.contains(songs[1]);

        if(found) {
            System.out.println("FAILED: unittest4::subtest 6::contains failed");
            success = false;
        }
        // Validate the state of the catalog did not change
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest4::subtest 7::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 5) {
            System.out.println("FAILED: unittest4::subtest 8::count==5 failed");
            success = false;
        }
        
        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest4::subtest 9::integrity check failed");
            success = false;
        }
        return success;
    }

    // Validates removal using search for the catalog
    public static boolean unittest5(MusicCatalog catalog) {
        boolean success = true;

        // add 3 songs into the catalog
        catalog.add(songs[4]);
        catalog.add(songs[5]);
        catalog.add(songs[6]);

        // Validate the state of the catalog reflects that there are 3
        // songs in the catalog
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest5::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 3) {
            System.out.println("FAILED: unittest5::subtest 2::count==3 failed");
            success = false;
        }

        // Remove the first song
        Song song = catalog.remove();

        // Validate the state of the catalog reflects that there are only 2
        // songs remaining in the catalog
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest5::subtest 3::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 2) {
            System.out.println("FAILED: unittest5::subtest 4::count==2 failed");
            success = false;
        }
        if(song != songs[4]) {
            System.out.println("FAILED: unittest5::subtest 5::remove song validation failed");
            success = false;
        }
        
        // Remove a specific song
        song = catalog.remove(songs[6]);

        // Validate the state of the catalog reflects that there is only 1
        // song remaining in the catalog
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest5::subtest 6::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 1) {
            System.out.println("FAILED: unittest5::subtest 7::count==1 failed");
            success = false;
        }
        if(song != songs[6]) {
            System.out.println("FAILED: unittest5::subtest 8::remove song validation failed");
            success = false;
        }
        
        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest5::subtest 9::integrity check failed");
            success = false;
        }
        return success;
    }

    /// Validates clearing the catalog
    public static boolean unittest6(MusicCatalog catalog) {
        boolean success = true;

        // add 3 songs into the catalog
        catalog.add(songs[4]);
        catalog.add(songs[5]);
        catalog.add(songs[6]);

        // Clear the catalog
        catalog.clear();

        // Validate that the catalog is now empty
        if(!catalog.isEmpty()) {
            System.out.println("FAILED: unittest6::subtest 1::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 0) {
            System.out.println("FAILED: unittest6::subtest 2::count==0 failed");
            success = false;
        }
        
        // add another song into the catalog
        catalog.add(songs[0]);

        // Validate that the catalog is no longer empty
        if(catalog.isEmpty()) {
            System.out.println("FAILED: unittest6::subtest 3::isEmpty failed");
            success = false;
        }
        if(catalog.count() != 1) {
            System.out.println("FAILED: unittest6::subtest 4::count==1 failed");
            success = false;
        }
        
        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest6::subtest 5::integrity check failed");
            success = false;
        }
        return success;
    }

    /// Validates indexed based retrieval from the catalog
    public static boolean unittest7(MusicCatalog catalog) {
        boolean success = true;
    
        // Validate indexing for an empty catalog is properly handled
        if(catalog.get(-1) != null) {
            System.out.println("FAILED: unittest7::subtest 1::get(-1) test failed");
            success = false;
        }
        if(catalog.get(0) != null) {
            System.out.println("FAILED: unittest7::subtest 2::get(0) test failed");
            success = false;
        }

        // Add a song to the catalog
        catalog.add(songs[4]);

        // Validate indexing for a catalog with one item in it.
        if(catalog.get(-1) != null) {
            System.out.println("FAILED: unittest7::subtest 3::get(-1) test failed");
            success = false;
        }
        if(catalog.get(1) != null) {
            System.out.println("FAILED: unittest7::subtest 4::get(1) test failed");
            success = false;
        }

        Song song = catalog.get(0);
        if(song != songs[4]) {
            System.out.println("FAILED: unittest7::subtest 5::song validation failed");
            success = false;
        }

        // Validate the integrity of the catalog
        if(!catalog.isIntegrityValid()) {
            System.out.println("FAILED: unittest7::subtest 6::integrity check failed");
            success = false;
        }
        return success;
    }
}
