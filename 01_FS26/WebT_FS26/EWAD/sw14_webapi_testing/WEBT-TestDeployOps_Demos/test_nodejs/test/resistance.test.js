import assert from 'node:assert/strict';
import { describe, it } from 'node:test';
import calculateResistance from '../main/resistance.js';

describe('resistance', function () {
  it('calculate for wiring=serial, r1=0, and r2=5', function () {    
    assert.strictEqual(calculateResistance(0, 5, 'serial'), 5);
  });
});
describe('resistance', function () {
  it('calculate for wiring=serial, r1=100, and r2=0', function () {
    assert.strictEqual(calculateResistance(100, 0, 'serial'), 100);
  });
});
describe('resistance', function () {
  it('calculate for wiring=serial, r1=100.5, and r2=20.3', function () {
    assert.strictEqual(calculateResistance(100.5, 20.3, 'serial'), 120.8);
  });
});
describe('resistance', function () {
  it('calculate for wiring=parallel, r1=4, and r2=6', function () {
    assert.strictEqual(calculateResistance(4, 6, 'parallel'), 2.4);
  });
});
describe('resistance', function () {
  it('calculate for wiring=parallel, r1=34, and r2=16', function () {
    assert.strictEqual(calculateResistance(34, 16, 'parallel'), 10.88);
  });
});
