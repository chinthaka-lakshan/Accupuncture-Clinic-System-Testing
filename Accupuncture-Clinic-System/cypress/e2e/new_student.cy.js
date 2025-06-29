describe('Add New Student Form', () => {
  it('fills out and submits the student form successfully', () => {
    cy.visit('http://localhost:3000/students/new');

    cy.get('#STUDENTID').type('401');
    cy.get('#STUDENTNAME').type('Hasitha Wijewardhana');
    cy.get('#CONTACTNO').type('0771234567');

    cy.get('button[type="submit"]').click();

    cy.url().should('include', '/students');
  });
});
