package com.iexceed.appzillonbanking.onboarding;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.iexceed.appzillonbanking.onboarding");

        noClasses()
            .that()
            .resideInAnyPackage("com.iexceed.appzillonbanking.onboarding.service..")
            .or()
            .resideInAnyPackage("com.iexceed.appzillonbanking.onboarding.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.iexceed.appzillonbanking.onboarding.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
