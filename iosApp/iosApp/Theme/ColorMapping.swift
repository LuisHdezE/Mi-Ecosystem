import SwiftUI
import Shared

extension ColorToken {
    func toSwiftUIColor() -> Color {
        let argbValue = self.argb
        let a = Double((argbValue >> 24) & 0xFF) / 255.0
        let r = Double((argbValue >> 16) & 0xFF) / 255.0
        let g = Double((argbValue >> 8) & 0xFF) / 255.0
        let b = Double(argbValue & 0xFF) / 255.0

        return Color(red: r, green: g, blue: b, opacity: a)
    }
}
