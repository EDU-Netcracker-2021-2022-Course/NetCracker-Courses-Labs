package utils.validator;

import contract.Contract;
import utils.validator.modelChecker.ContractChecker;
import utils.validator.modelChecker.PersonChecker;

public class ContractValidator {

    public ValidatorReport validate(Contract contract){
        ValidatorReport report = new ValidatorReport();

        ContractChecker contractChecker = new ContractChecker();
        PersonChecker personChecker = new PersonChecker();
        report.setStatus("ok");

        if(!(contractChecker.checkId(contract))){
            report.setStatus("error");
            report.addErrorDescription("Contract UUID is null");
            report.addErrorVariableName("Contract|id");
        }

        if(!(contractChecker.checkNumber(contract))){
            report.setStatus("error");
            report.addErrorDescription("Contract number variable is incorrect. Probably number is negative or equals 0.");
            report.addErrorVariableName("Contract|number");
        }

        if(!(contractChecker.checkStartingDate(contract))){
            report.setStatus("error");
            report.addErrorDescription("Contract startingDate variable is incorrect. Probably the date is null or lies outside the valid time range.");
            report.addErrorVariableName("Contract|startingDate");
        }

        if(!(contractChecker.checkEndingDate(contract))){
            report.setStatus("error");
            report.addErrorDescription("Contract endingDate variable is incorrect. Probably the date is null or lies outside the valid time range.");
            report.addErrorVariableName("Contract|endingDate");
        }

        if(!(contractChecker.checkOwner(contract))){
            report.setStatus("error");
            report.addErrorDescription("Contract owner is incorrect");
            report.addErrorVariableName("Contract|owner");
        } else {

            if(!(personChecker.checkId(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person id is null");
                report.addErrorVariableName("Person|id");
            }

            if(!(personChecker.checkFirstName(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person firstname is in the wrong format. Probably firstname contains digits and wrong symbols.");
                report.addErrorVariableName("Person|firstname");
            }

            if(!(personChecker.checkMiddleName(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person middlename is in the wrong format. Probably middlename contains digits and wrong symbols.");
                report.addErrorVariableName("Person|middlename");
            }

            if(!(personChecker.checkLastName(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person lastname is in the wrong format. Probably lastname contains digits and wrong symbols.");
                report.addErrorVariableName("Person|lastname");
            }

            if(!(personChecker.checkBirthDate(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person birthDate is incorrect. Date may be null or lies outside the valid time range");
                report.addErrorVariableName("Person|birthDate");
            }

            if(!(personChecker.checkPassportSeries(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person passportSeries is invalid. This field should contain 6 digits.");
                report.addErrorVariableName("Person|passportSeries");
            }

            if(!(personChecker.checkPassportNumber(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person passportNumber is invalid. This field should contain 4 digits.");
                report.addErrorVariableName("Person|passportNumber");
            }

            if(!(personChecker.checkAge(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person's age is incorrect. Person's age should be more than 18.");
                report.addErrorVariableName("Person|age");
            }

            if(!(personChecker.checkSex(contract.getOwner()))){
                report.setStatus("error");
                report.addErrorDescription("Person's sex is incorrect.");
                report.addErrorVariableName("Person|sex");
            }
        }

        return report;
    }
}
