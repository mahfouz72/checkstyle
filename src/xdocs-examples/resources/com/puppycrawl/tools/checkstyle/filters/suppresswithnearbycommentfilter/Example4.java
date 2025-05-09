/*xml
<module name="Checker">
  <module name="TreeWalker">
    <module name="SuppressWithNearbyCommentFilter">
      <property name="commentFormat"
          value="CHECKSTYLE IGNORE (\w+) FOR NEXT (\d+) LINES"/>
      <property name="checkFormat" value="$1"/>
      <property name="influenceFormat" value="$2"/>
    </module>
    <module name="ConstantName"/>
  </module>
</module>
*/
package com.puppycrawl.tools.checkstyle.filters.suppresswithnearbycommentfilter;
// xdoc section -- start
public class Example4 {
  // CHECKSTYLE IGNORE ConstantNameCheck FOR NEXT 8 LINES
  // filtered violation below 'must match pattern'
  static final int lowerCaseConstant1 = 1;
  // filtered violation below 'must match pattern'
  static final int lowerCaseConstant2 = 2;
  // filtered violation below 'must match pattern'
  static final int lowerCaseConstant3 = 3;
  // filtered violation below 'must match pattern'
  static final int lowerCaseConstant4 = 4;
  static final int lowerCaseConstant5 = 5; // violation 'must match pattern'
}
// xdoc section -- end
