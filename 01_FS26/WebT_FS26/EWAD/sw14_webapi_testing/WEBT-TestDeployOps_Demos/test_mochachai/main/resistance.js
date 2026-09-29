function calculateResistance(r1, r2, wiring) {
    let totalResistance;
    if (wiring == 'serial') {
        totalResistance = r1 + r2;
    } else if (wiring == 'parallel') {
        totalResistance = r1 * r2 / (r1 + r2);
    }
    return totalResistance;
}
