import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function passwordValidator(): ValidatorFn {
    return (ctrl: AbstractControl): null | ValidationErrors => {
        const value = ctrl.value;

        const checks = [
            {
                name: "isLengthValid",
                check: value.length >= 8,
                error: "8 caractères"
            },
            {
                name: "containsLowerCase",
                check: /[a-z]/.test(value),
                error: "1 minuscule"
            },
            {
                name: "containsUpperCase",
                check: /[A-Z]/.test(value),
                error: "1 majuscule"
            },
            {
                name: "containsNumber",
                check: /[0-9]/.test(value),
                error: "1 chiffre"
            },
            {
                name: "containsSpecialChar",
                check: /[<>,?;.:/!§%*µ$£^¨¤&~"#'\{\(\[|`_\\@=\]\)\}-]/.test(value),
                error: "1 caractère spécial"
            }
        ];

        const isFieldValid = checks.reduce((acc, curr): boolean => {
            return acc && curr.check
        }, true);

        if (isFieldValid) {
            return null;
        } else {
            const error = checks.reduce((acc, curr): string => {
                return !curr.check ? `${acc} ${curr.error},` : acc;
            }, "Ce champ doit contenir au moins").slice(0, -1);

            return {
                password: error
            };
        }
    }
}