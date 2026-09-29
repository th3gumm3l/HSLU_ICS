// ensures that input is converted to a string
function toString(text) {
    return text + "";
}

// returns number of characters in a text
function charCount(text) {
    return toString(text).length;
}

// returns number of words in a text
function wordCount(text) {    
    let splittedText = toString(text).split(/( |\n)/g);
    let wordCount = 0;
    for (let word of splittedText) {
        if (word.trim().length > 0) {
            ++wordCount;
        }
    }
    return wordCount;
}

// returns number of lines in a text
function lineCount(text) {
    return toString(text).split('\n').length;
}
