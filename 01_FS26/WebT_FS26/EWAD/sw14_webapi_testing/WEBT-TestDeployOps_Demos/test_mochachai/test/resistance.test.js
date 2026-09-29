describe('resistance', function () {
  it('calculate for wiring=serial, r1=0, and r2=5', function () {
    chai.expect(calculateResistance(0, 5, 'serial')).to.equal(5);
  });
});
describe('resistance', function () {
  it('calculate for wiring=serial, r1=100, and r2=0', function () {
    chai.expect(calculateResistance(100, 0, 'serial')).to.equal(100);
  });
});
describe('resistance', function () {
  it('calculate for wiring=serial, r1=100.5, and r2=20.3', function () {
    chai.expect(calculateResistance(100.5, 20.3, 'serial')).to.equal(120.8);
  });
});
describe('resistance', function () {
  it('calculate for wiring=parallel, r1=4, and r2=6', function () {
    chai.expect(calculateResistance(4, 6, 'parallel')).to.equal(2.4);
  });
});
describe('resistance', function () {
  it('calculate for wiring=parallel, r1=34, and r2=16', function () {
    chai.expect(calculateResistance(34, 16, 'parallel')).to.equal(10.88);
  });
});
