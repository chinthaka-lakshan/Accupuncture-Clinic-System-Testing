describe('Add New Inventory Item Form', () => {
  it('fills out and submits the form successfully', () => {
    // ✅ Visit the actual working route from your App.js
    cy.visit('http://localhost:3000/inventory/new');

    // Fill form fields using your existing IDs
    cy.get('#ITEMID').type('1002');
    cy.get('#ITEM_NAME').type('Acupuncture Needles');
    cy.get('#VENDOR\\ NAME').type('Wellness Suppliers'); // space needs escaping
    cy.get('#UNIT_PRICE').type('3000');

    // Submit the form
    cy.get('button[type="submit"]').click();

    // Verify redirect to /inventory
    cy.url().should('include', '/inventory');
  });
});
