/**
 * Author: Shrill Patel
 * Revised: April 12, 2021
 * 
 * Description: A file that runs all of the junit tests.
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestBoard.class,
   TestTileT.class,
   TestGame.class
})

public class AllTests
{
}
