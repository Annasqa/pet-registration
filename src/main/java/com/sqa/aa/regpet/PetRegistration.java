/**
 *   File Name: PetRegistration.java<br>
 *
 *   LastName, FirstName<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Apr 2, 2016
 *
 */

package com.sqa.aa.regpet;

import com.sqa.aa.util.helpers.*;

/**
 * PetRegistration //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 *
 */
public class PetRegistration {
	//
	public static enum Pet {
		BIRD, CAT, DINASURE, DOG, FISH, HORSE, REPTILE
	}

	private static String appName = "Pet Registration 101";
	private static int[] petAges;
	private static char[] petGenders;

	private static boolean[] petHasRabiesShots;
	private static String[] petNames, petAddresses;
	private static String[] petTypes;
	private static double[] petYearlyFees;
	private static String regPetsString = "";
	private static String userName;
	static int numOfActualPetRegistrations;

	static int numOfRequestPetRegistrations;

	public static void registerPets() {
		welcomeUser();
		requestPetRegistration();
		requestPetDetails();
		displayPetRegistrationInformation();
		farewellUser();

	}

	/**
	 * @param resultString
	 * @return
	 */
	private static boolean checkIfUserCanAfford(String resultString) {
		boolean canAfford = false;
		double petRegPrice;
		switch (resultString) {
		case "CAT":
			petRegPrice = 35.00;
			break;
		case "DOG":
			petRegPrice = 55.00;
			break;
		case "FISH":
			petRegPrice = 5.50;
			break;
		case "HORSE":
			petRegPrice = 1200.00;
			break;
		case "REPTILE":
			petRegPrice = 22.22;
			break;
		case "BIRD":
			petRegPrice = 17.75;
			break;
		default:
			System.out.println("Sorry this pet can not be registered.");
			return false;
		}
		canAfford = RequestInput.getBoolean(
				String.format("It costs $%,.2f to register a %s, would you like to continue registering it? ",
						petRegPrice, resultString.toLowerCase()));
		return canAfford;
	}

	/**
	 *
	 */
	private static void displayPetRegistrationInformation() {
		System.out.println("Request: " + numOfRequestPetRegistrations);
		System.out.println("Actual: " + numOfActualPetRegistrations);
		System.out.println("Pets " + regPetsString);

	}

	/**
	 *
	 */
	private static void farewellUser() {
		AppBasics.farewellUser(userName, appName);

	}

	/**
	 *
	 */
	private static void requestPetDetails() {
		String genderPrefixA;
		String genderPrefixB;
		petTypes = regPetsString.split(":");
		petAges = new int[petTypes.length];
		petGenders = new char[petTypes.length];
		petHasRabiesShots = new boolean[petTypes.length];
		petNames = new String[petTypes.length];
		petAddresses = new String[petTypes.length];
		petYearlyFees = new double[petTypes.length];
		for (int i = 0; i < petTypes.length; i++) {
			System.out.println("Registration for " + petTypes[i].toLowerCase() + ": ");
			petNames[i] = RequestInput.getString("\tWhat is your " + petTypes[i].toLowerCase() + "'s name: ");
			petAges[i] = RequestInput.getInt("\tHow old is " + petNames[i] + ";");
			petGenders[i] = RequestInput
					.getChar("Is " + petNames[i] + "a male(M) or female(F)" + petTypes[i].toLowerCase() + ": ");
			if (petGenders[i] == ('M')) {
				genderPrefixA = "he";
				genderPrefixB = "his";
			} else {
				genderPrefixA = "she";
				genderPrefixB = "her";
			}
			petHasRabiesShots[i] = RequestInput
					.getBoolean("\tDoes " + genderPrefixA + " have " + genderPrefixB + " recent Rabies shot: ");
			petYearlyFees[i] = RequestInput
					.getDouble("\tHow much on average does " + genderPrefixA + " cost roughly a year? ");
			petAddresses[i] = RequestInput.getString("\tWhat is " + genderPrefixA + " current address: ");
		}

	}

	/**
	 *
	 */
	private static void requestPetRegistration() {
		numOfRequestPetRegistrations = retrievePetRegistrationCount();
		numOfActualPetRegistrations = requestTypeOfPetAndPriceConfirmation();
	}

	/**
	 * @param numOfRequestPetRegistrations2
	 * @return
	 */
	private static int requestTypeOfPetAndPriceConfirmation() {
		boolean willRegister;
		String resultString;
		int registrationCount = 0;
		boolean canAfford = false;

		for (int i = 0; i < numOfRequestPetRegistrations; i++) {

			resultString = RequestInput.getString("Can you give me the type of Pet " + (i + 1) + "? ",
					"Would you like to cancel you current registration for your %s?", Pet.values());
			if (resultString.length() > 0) {
				canAfford = checkIfUserCanAfford(resultString);
				if (canAfford) {

					if (regPetsString.length() > 1) {
						regPetsString += ":" + resultString;
					} else {
						regPetsString += resultString;
					}
					registrationCount++;
				}
			}
		}

		return registrationCount;
	}

	/**
	 * @return
	 */
	private static int retrievePetRegistrationCount() {

		return RequestInput.getInt("How many pets would you like to register into the system:",
				"Sorry, you can only register between %d and %d pets at time.", 1, 5);
	}

	/**
	 *
	 */
	private static void welcomeUser() {
		userName = AppBasics.welcomeUserAndGetUserName(appName);

	}

}
