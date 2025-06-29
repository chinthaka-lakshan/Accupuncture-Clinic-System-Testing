describe('Add New Patient Form', () => {
  it('fills out and submits the patient form successfully', () => {
    cy.visit('http://localhost:3000/patients/new'); // Adjust URL if different

    cy.get('#PATIENTID').type('301');
    cy.get('#NAME').type('Hasitha Wijewardhana');
    cy.get('#AGE').type('30');
    cy.get('#PHONENO').type('0712345678');
    cy.get('#ADDRESS').type('No. 123, Colombo');
    
    // Assuming gender radio inputs have id 'male', 'female', 'other'
    cy.get('#male').check();

    cy.get('button[type="submit"]').click();

    // Confirm redirection after submit
    cy.url().should('include', '/patients');
  });
});
