describe('Login Page - ZenCare', () => {
  it('logs in with correct credentials', () => {
    cy.visit('http://localhost:3000');

    // Use placeholder to select fields
    cy.get('input[placeholder="Enter Username"]').type('kiu');
    cy.get('input[placeholder="Enter Password"]').type('kiu@123');

    // Use button by type
    cy.get('button[type="submit"]').click();

    // Verify redirection
    cy.url().should('include', '/home');
  });

  it('shows error for wrong login', () => {
    cy.visit('http://localhost:3000');

    cy.get('input[placeholder="Enter Username"]').type('wrong');
    cy.get('input[placeholder="Enter Password"]').type('wrong');

    cy.get('button[type="submit"]').click();

    cy.on('window:alert', (text) => {
      expect(text).to.contains('Invalid username or password');
    });
  });
});
