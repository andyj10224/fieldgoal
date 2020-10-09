public class FieldGoal {

    /**
     * A computer program designed to be used by fans and kicking coaches for field goal attempts.
     *
     * @author Andy Jiang
     * @version 1.0
     */

    private static final double g = 32.174049; //Acceleration due to gravity (in ft/s^2)
    private static final double fieldGoalHeight = 12; //Height of field goal... added 2 feet for safety
    private static final double hashMark = 20; //Distance of hashMark from center of field is 20 ft in college

    public static void main(String[] args) {
        /**
        // Extra Point
        if (isGood(20, false, 7, 5, 70, 45)) {
            System.out.println("Kick is good!!!");
        }

        // Chipshot 30 yard attempt
        if (isGood(30, true, 7, 5, 70, 45)) {
            System.out.println("Kick is good!!!");
        }

        // 40 yard attempt
        if (isGood(40, true, 7, 5, 70, 45)) {
            System.out.println("Kick is good!!!");
        }

        // 50 yard attempt
        if (isGood(50, true, 7, 5, 70, 45)) {
            System.out.println("Kick is good!!!");
        }

         */

        // Harrison Butker 53 yard FG vs UGA 2014
        if (isGood(53, true, 7, 5, 75, 45)) {
            System.out.println("Tech has tied the game!!!");
        }
    }

    /**
     * Determines if the kick will be good
     *
     * @param yards The yardage of the field goal attempt
     * @param onHashMark if the kick is on a hash mark
     * @param maxPlayerHeight the height of the tallest blocking player (in ft) (expect 7 ft)
     * @param maxVerticalJump highest vertical jump of any player (in ft) (expect 3 ft, or 4-5 feet for exceptional players)
     * @param kickSpeed The speed that the ball is kicked with (in ft/s) (aim for 70 ft/s)
     * @param kickAngle The angle of the kick (in degrees) (aim for 40 - 50 degrees)
     * @return If the kick was successful
     */

    public static boolean isGood(double yards, boolean onHashMark, double maxPlayerHeight, double maxVerticalJump, double kickSpeed, double kickAngle) {
        // The defense is 7 yards, or 21 ft, away... Ball needs to clear the max defender height
        if (height(21, kickSpeed, kickAngle) > maxPlayerHeight + maxVerticalJump + 3) {
            double clearingHeight = height(21, kickSpeed, kickAngle) - maxPlayerHeight - maxVerticalJump - 3;
            System.out.println("Not blocked!!!");
            System.out.println("Cleared defender by " + clearingHeight + " feet!!!");
        } else {
            System.out.println("BLOCKED!!!");
            return false;
        }

        // Field goal distance, in feet, Accounts for hashMark in field goal attempt
        double fgDistance = Math.sqrt(9 * yards * yards + hashMark * hashMark);

        if (height(fgDistance, kickSpeed, kickAngle) > fieldGoalHeight) {
            double aboveCrossbar = height(fgDistance, kickSpeed, kickAngle) - fieldGoalHeight;
            System.out.println("Cleared crossbar by " + aboveCrossbar + " feet.");
            return true;
        } else {
            System.out.println("Kick is short!!!");
            return false;
        }
    }

    /**
     * The height of the ball given it's position... derived using kinematics
     * @param position Horizontal distance the ball is from it's starting point
     * @param kickSpeed Speed of FG Kick
     * @param kickAngle Angle of FG Kick
     * @return Height of ball at point
     */
    private static double height(double position, double kickSpeed, double kickAngle) {
        double theta = Math.toRadians(kickAngle);
        double horizontalSpeed = kickSpeed * Math.cos(theta);
        return position * Math.tan(theta) - 0.5 * g * position * position / (horizontalSpeed * horizontalSpeed);
    }
}
